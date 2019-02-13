package http.v1_1.format;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public abstract class HttpMessagePart {

    private static Map<Class<? extends HttpMessagePart>, Pattern> formatPatterns = new HashMap<>();
    private static Map<Class<? extends HttpMessagePart>, Integer> expectedGroups = new HashMap<>();

    protected HttpMessagePart(final String value) throws WrongFormat {
        if (value == null)
            throw new NullPointerException("An HTTP message part can never be null, not even "
                    + this.getClass().getSimpleName() + ".");
        Matcher valueMatcher = verify(value);
        populateFields(value, valueMatcher);
    }

    private String getErrorMessageSettingField(final Field field, final Object value,
            final String whathappened) {
        return "Failed to set annotated field " + field.getName() + " of type "
                + field.getType().getSimpleName() + " to value " + value.toString() + " (of type "
                + value.getClass().getSimpleName() + "). (" + whathappened
                + ")";
    }

    private void setField(final Field field, final Object value) {
        try {
            field.set(this, value);
        } catch (IllegalArgumentException | IllegalAccessException exception) {
            throw new FieldPopulationException(
                    getErrorMessageSettingField(
                            field,
                            value,
                            "Setting the field directly failed."));

        }
    }

    private void setPrimitiveField(final Field field, final String value) {
        Class<?> primitiveClass = field.getType();
        try {
            if (Byte.class.equals(primitiveClass))
                setField(field, Byte.valueOf(value));
            if (Short.class.equals(primitiveClass))
                setField(field, Short.valueOf(value));
            if (Integer.class.equals(primitiveClass))
                setField(field, Integer.valueOf(value));
            if (Long.class.equals(primitiveClass))
                setField(field, Long.valueOf(value));
            if (Float.class.equals(primitiveClass))
                setField(field, Float.valueOf(value));
            if (Double.class.equals(primitiveClass))
                setField(field, Double.valueOf(value));
        } catch (NumberFormatException exception) {
            throw new FieldPopulationException(getErrorMessageSettingField(
                    field,
                    value,
                    "Casting to primitive type '" + primitiveClass.getSimpleName() + "' failed."),
                    exception);
        }
    }

    protected void populateFields(final String inputValue, final Matcher matcher) {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            RegexGroup annotation = field.getAnnotation(RegexGroup.class);
            if (annotation != null) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Class<?> clazz = field.getType();
                String matchedString = matcher.group(annotation.value());
                if (String.class.equals(clazz)) {
                    setField(field, matchedString);
                } else if (clazz.isPrimitive()) {
                    setPrimitiveField(field, matchedString);
                } else {
                    final String whatImTryingToDo = field.getType()
                            + "(\"" + matchedString
                            + "\")";
                    Object newlyCreatedItem;
                    try {
                        Constructor<?> constructor = clazz
                                .getDeclaredConstructor(String.class);
                        newlyCreatedItem = constructor.newInstance(matchedString);
                    } catch (NoSuchMethodException | SecurityException
                            | IllegalAccessException exception) {
                        throw new FieldPopulationException(
                                getErrorMessageSettingField(
                                        field,
                                        whatImTryingToDo,
                                        "Access to the String constructor failed."),
                                exception);
                    } catch (InvocationTargetException exception) {
                        throw new FieldPopulationException(
                                getErrorMessageSettingField(
                                        field,
                                        whatImTryingToDo,
                                        "The called constructor threw an exception."),
                                exception);
                    } catch (InstantiationException exception) {
                        throw new FieldPopulationException(
                                getErrorMessageSettingField(
                                        field,
                                        whatImTryingToDo,
                                        "Instantiation of the class failed. It might not be concrete."),
                                exception);
                    } catch (IllegalArgumentException exception) {
                        throw new FieldPopulationException(
                                getErrorMessageSettingField(
                                        field,
                                        whatImTryingToDo,
                                        "The String argument seems wrong."),
                                exception);
                    }
                    setField(field, newlyCreatedItem);
                }
            }
        }
    }

    protected synchronized Pattern getFormatPattern() {
        Pattern pattern = formatPatterns.get(this.getClass());
        if (pattern != null)
            return pattern;
        Format format = this.getClass().getAnnotation(Format.class);
        if (format == null) {
            throw new MissingFormatException(this.getClass().getSimpleName()
                    + " must either specify a @Format annotation or overwrite getFormatPattern().");
        }
        String regex = format.value();
        pattern = Pattern.compile(regex);
        try {
            formatPatterns.put(this.getClass(), pattern);
        } catch (PatternSyntaxException ex) {
            throw new MissingFormatException("String '" + regex + "' on class "
                    + this.getClass().getSimpleName() + " is not a valid regex pattern.", ex);
        }
        expectedGroups.put(this.getClass(), format.expectedGroups());
        return pattern;
    }

    public Matcher verify(final String in) throws WrongFormat {
        Matcher valueMatcher = getFormatPattern().matcher(in);
        if (!valueMatcher.matches()) {
            throw new WrongFormat(
                    "The given input didn't match the corresponding '"
                            + this.getClass().getSimpleName() + "' pattern.",
                    getFormatPattern());
        }
        int expectedGroupCount = expectedGroups.get(this.getClass());
        if ((expectedGroupCount != -1) && (valueMatcher.groupCount() != expectedGroupCount)) {
            throw new WrongFormat(
                    "The given input (\"" + in + "\") did match the provided pattern (\""
                            + getFormatPattern() + "\") for class "
                            + this.getClass().getSimpleName() + ", but it contained "
                            + valueMatcher.groupCount()
                            + " groups instead of the expected " + expectedGroupCount + ".",
                    getFormatPattern());

        }
        return valueMatcher;
    }
}

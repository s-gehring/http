package http.v1_1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.junit.Test;

public class TestFormatPatternRegEx {

    private boolean failFields(final List<Field> fields) throws Exception {
        if (fields.isEmpty())
            return true;
        System.err.println("The following fields are wrong:");
        for (Field field : fields) {
            System.err.println("Field " + field.getName() + ":");
            System.err.println("\t" + field.get(null).toString());
        }
        return false;
    }

    @Test
    public void testRegExCompilation() throws Exception {
        Field[] allFields = Patterns.class.getDeclaredFields();
        List<Field> fields = new ArrayList<>();
        long allSize = 0L;
        for (Field field : allFields) {
            assertEquals("Field is not of type 'String'.", String.class, field.getType());

            field.setAccessible(true);
            allSize += ((String) field.get(null)).length();

            fields.add(field);
        }
        System.out.println("The whole class has an estimated size of " + allSize + "B");
        Collections.sort(fields, (o1, o2) -> {
            try {

                String lhs = (String) o1.get(null);
                String rhs = (String) o2.get(null);
                return -Integer.compare(lhs.length(), rhs.length());
            } catch (IllegalArgumentException | IllegalAccessException exception) {
                throw new RuntimeException("Something went wrong comparing strings.", exception);
            }
        });
        List<Field> failedFields = new ArrayList<>();
        allSize = 0L;
        for (Field field : fields) {
            try {
                Pattern.compile(field.get(null).toString());

            } catch (PatternSyntaxException ex) {
                failedFields.add(field);
            }
        }
        assertTrue("There are failing fields.", failFields(failedFields));
    }
}

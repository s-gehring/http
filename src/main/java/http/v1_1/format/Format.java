package http.v1_1.format;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Format {

    /**
     * The corresponding regex.
     *
     */
    String value();

    /**
     * The number of expected groups from the given regex. (-1 to disable check)
     */
    int expectedGroups() default -1;
}

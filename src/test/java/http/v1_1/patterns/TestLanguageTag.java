package http.v1_1.patterns;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import http.v1_1.Patterns;

@RunWith(Parameterized.class)
public class TestLanguageTag {

    @Parameters(name = "Test {index}: \"{0}\" (expect {1})")
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
            // @formatter:off
            { "en", true },
            { "en-US", true },
            { "en-cockney", true },
            { "i-cherokee", true },
            { "x-pig-latin", true },
            { "x- pig -latin", false },
            { "e n", false },
            { "en US", false },
            { "en - US", false },
            { "i ", false }
            // @formatter:on
        });
    }

    private final String  toTest;
    private final boolean validates;

    public TestLanguageTag(final String toTest, final boolean validates) {
        this.toTest = toTest;
        this.validates = validates;
    }

    @Test
    public void testFormat() {

        Pattern pattern = Pattern.compile(Patterns.LANGUAGE_TAG);
        Matcher matcher = pattern.matcher(toTest);
        if (matcher.matches() != validates) {
            fail("Test string '" + toTest + "' does " + (validates ? "" : "not ") + "match '"
                    + pattern.toString() + "', which is wrong.");
        }
    }
}
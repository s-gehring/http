package http.v1_1.patterns;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import http.v1_1.Patterns;

@RunWith(Parameterized.class)
public class TestDtext {

    @Parameters(name = "Test {index}: \"{0}\" (expect {1})")
    public static Collection<Object[]> parameters() {
        Pattern LWS = Pattern.compile(Patterns.LWS);
        List<Object[]> parameters = new ArrayList<>();
        for (char curChar = 0; curChar < 128; ++curChar) {
            switch (curChar) {
                case '\\':
                case '[':
                case ']':
                case '\r':
                    parameters.add(new Object[] {
                            String.valueOf(curChar), false
                    });
                    break;
                default:
                    parameters.add(new Object[] {
                            String.valueOf(curChar), true
                    });
            }
        }
        for (char curChar = 0; curChar < 128; ++curChar) {
            String curStr = String.valueOf(curChar) + String.valueOf(curChar);
            boolean validates = LWS.matcher(curStr).matches();
            parameters.add(new Object[] {
                    String.valueOf(curChar) + String.valueOf(curChar), validates
            });
        }
        return parameters;
    }

    private final String  toTest;
    private final boolean validates;

    public TestDtext(final String toTest, final boolean validates) {
        this.toTest = toTest;
        this.validates = validates;
    }

    @Test
    public void testFormat() {

        Pattern pattern = Pattern.compile(Patterns.DTEXT);
        Matcher matcher = pattern.matcher(toTest);
        if (matcher.matches() != validates) {
            Character target = toTest.charAt(0);
            fail("Character '" + target + "' (int " + (int) target + ", hex "
                    + Integer.toHexString(target) + ") failed (expected "
                    + (validates ? "a " : "NO ") + "match). (" + toTest.length() + " characters)");
        }

    }
}

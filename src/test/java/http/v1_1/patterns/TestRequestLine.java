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
public class TestRequestLine {

    @Parameters(name = "Test {index}: \"{0}\" (expect {1})")
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
            // @formatter:off
            { "OPTIONS * HTTP/1.1\r\n", true },
            { "GET http://www.w3.org/pub/WWW/TheProject.html HTTP/1.1\r\n", true },
            { "GET /pub/WWW/TheProject.html HTTP/1.1\r\n", true },
            { "GET / HTTP/1.1\r\n", true },
            { "GET  HTTP/1.1\r\n", false },
            { "OPTIONS * HTTP/1.1", false },
            { "GET http://www.w3.org/pub/WWW/TheProject.html HTTP/1.1", false },
            { "GET /pub/WWW/TheProject.html HTTP/1.1", false },
            { "GET / HTTP/1.1", false },
            { "GET  HTTP/1.1", false }
            // @formatter:on
        });
    }

    private final String  toTest;
    private final boolean validates;

    public TestRequestLine(final String toTest, final boolean validates) {
        this.toTest = toTest;
        this.validates = validates;
    }

    @Test
    public void testFormat() {

        Pattern pattern = Pattern.compile(Patterns.REQUEST_LINE);
        Matcher matcher = pattern.matcher(toTest);
        if (matcher.matches() != validates) {
            fail("Test string '" + toTest + "' does " + (validates ? "" : "not ") + "match '"
                    + pattern.toString() + "', which is wrong.");
        }
    }
}
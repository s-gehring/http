package http.v1_1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import http.v1_1.format.WrongFormat;

public class TestBodyFormatInit {

    private void testEquality(final String toTest) throws WrongFormat {
        Body body = new Body(toTest);
        assertEquals("Body wasn't parsed correctly.", toTest, body.getBody());
    }

    @Test
    public void testCorrectBody() throws WrongFormat {

        testEquality("Hallo");
    }

    @Test
    public void testCorrectBodyWithBreaksUnix() throws WrongFormat {
        testEquality("Hallo\\nHallo\\n Hallo");
    }

    @Test
    public void testCorrectBodyWithBreaksMac() throws WrongFormat {
        testEquality("Hallo\\rHallo\\r Hallo");
    }

    @Test
    public void testCorrectBodyWithBreaksWindows() throws WrongFormat {
        testEquality("Hallo\\r\\nHallo\\r\\n Hallo");
    }

    @Test
    public void testCorrectBodyWithBreaksMixed() throws WrongFormat {
        testEquality("Hallo\\r\\nHallo\\n Hallo\\rhallo");
    }

}

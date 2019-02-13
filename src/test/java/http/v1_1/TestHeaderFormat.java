package http.v1_1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.LinkedList;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import http.v1_1.format.WrongFormat;
import lombok.extern.slf4j.Slf4j;

@RunWith(Parameterized.class)
@Slf4j
public class TestHeaderFormat {

    @Parameters(name = "#{index} {2}")
    public static Collection<Object[]> getParameters() throws IOException {
        InputStream inStream = TestHeaderFormat.class.getClassLoader()
                .getResourceAsStream("format/header_tests.json");
        String jsonString = IOUtils.toString(inStream, StandardCharsets.UTF_8);
        JSONObject json = new JSONObject(jsonString);

        JSONArray valid = json.getJSONArray("valid");
        JSONArray invalid = json.getJSONArray("invalid");

        Collection<Object[]> result = new LinkedList<>();

        for (int i = 0; i < valid.length(); ++i) {
            JSONObject curIn = valid.getJSONObject(i);
            result.add(new Object[] {
                    curIn.getString("input"),
                    true,
                    curIn.getString("key"),
                    curIn.getString("value")
            });
        }
        for (int i = 0; i < invalid.length(); ++i) {
            JSONObject curIn = invalid.getJSONObject(i);
            String name;
            try {
                name = curIn.getString("key");
            } catch (JSONException e) {
                name = curIn.getString("input");
            }
            result.add(new Object[] {
                    curIn.getString("input"),
                    false,
                    "!" + name,
                    null
            });
        }
        return result;
    }

    private String  inputHeader;
    private boolean isCorrect;
    private String  expectedKey;
    private String  expectedValue;

    @Test
    public void test() {
        try {
            Header header = new Header(inputHeader);
            assertEquals("The key is not as expected.", expectedKey, header.getKey());
            assertEquals("The value is not as expected.", expectedValue, header.getValue());
        } catch (WrongFormat e) {
            if (isCorrect) {
                fail("Header '" + inputHeader + "' failed, although it should be correct. ("
                        + e.getMessage() + ")");
            }

        }
    }

    public TestHeaderFormat(
            final String input,
            final boolean isCorrect,
            final String key,
            final String value) {
        this.inputHeader = input;
        this.isCorrect = isCorrect;
        this.expectedKey = key;
        this.expectedValue = value;
        log.info("Testing header '" + input + "', which is " + (isCorrect ? "" : "NOT ")
                + "supposed to be correct.");

    }

}

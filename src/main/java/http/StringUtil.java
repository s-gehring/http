package http;

import java.io.IOException;
import java.io.Reader;

import lombok.experimental.UtilityClass;

@UtilityClass

public class StringUtil {

    public String readAll(final Reader reader) throws IOException {
        char[] arr = new char[8 * 1024];
        StringBuilder buffer = new StringBuilder();
        int numCharsRead;
        while ((numCharsRead = reader.read(arr, 0, arr.length)) != -1) {
            buffer.append(arr, 0, numCharsRead);
        }
        return buffer.toString();
    }

}

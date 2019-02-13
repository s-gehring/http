package http.url;

import java.util.regex.Pattern;

import http.MalformedArgumentException;
import http.v1_1.Patterns;
import lombok.Value;

@Value
public class Hostname {

    private static final Pattern VALID_HOST = Pattern.compile(Patterns.HOST);

    public static class MalformedHostnameException extends MalformedArgumentException {

        private static final long serialVersionUID = 7550492536976445627L;

        public MalformedHostnameException(final String msg) {
            super(msg);
        }

        public MalformedHostnameException(final String msg, final Throwable cause) {
            super(msg, cause);
        }
    }

    public Hostname(final String value) throws MalformedHostnameException {
        if (!VALID_HOST.matcher(value).matches()) {
            throw new MalformedHostnameException(
                    "Host name '" + value + "' is supposed to match RegEx("
                            + VALID_HOST.toString() + "), but it didn't.");
        }
        this.value = value;
    }

    String value;
}

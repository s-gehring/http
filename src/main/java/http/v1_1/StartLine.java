package http.v1_1;

import http.v1_1.format.Format;
import http.v1_1.format.HttpMessagePart;
import http.v1_1.format.RegexGroup;
import http.v1_1.format.WrongFormat;

public interface StartLine {

    @Format(Patterns.HTTP_VERSION)
    public static class HttpVersion extends HttpMessagePart {

        @RegexGroup(1)
        private int majorVersion;
        @RegexGroup(2)
        private int minorVersion;

        protected HttpVersion(final String value) throws WrongFormat {
            super(value);
        }

    }
}

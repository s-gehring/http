package http.url;

import http.MalformedRequestException;

public class MalformedUrlException extends MalformedRequestException {

    private static final long serialVersionUID = 7550492536976445628L;

    public MalformedUrlException(final String msg) {
        super(msg);
    }

    public MalformedUrlException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}

package http;

public class MalformedArgumentException extends Exception {

    private static final long serialVersionUID = 7550492536976445627L;

    public MalformedArgumentException(final String msg) {
        super(msg);
    }

    public MalformedArgumentException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}

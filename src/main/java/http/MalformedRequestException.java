package http;

public class MalformedRequestException extends RuntimeException {

    private static final long serialVersionUID = 7550492536976445627L;

    public MalformedRequestException(final String msg) {
        super(msg);
    }

    public MalformedRequestException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}

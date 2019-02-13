package http.v1_1.format;

public class MissingFormatException extends RuntimeException {

    private static final long serialVersionUID = -2607652237223147555L;

    public MissingFormatException(final String msg) {
        super(msg);
    }

    public MissingFormatException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

}

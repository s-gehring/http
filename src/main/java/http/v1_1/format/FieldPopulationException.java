package http.v1_1.format;

public class FieldPopulationException extends RuntimeException {

    private static final long serialVersionUID = -6038530666294043059L;

    public FieldPopulationException(final String msg) {
        super(msg);
    }

    public FieldPopulationException(final String msg, final Throwable cause) {
        super(cause);
    }
}

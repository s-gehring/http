package http.exceptions;

public class AnswerException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -8476042597203311339L;

    public AnswerException(final String msg) {
        super(msg);
    }

    public AnswerException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}

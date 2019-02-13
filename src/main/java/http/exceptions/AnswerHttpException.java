package http.exceptions;

public class AnswerHttpException extends AnswerException {

    /**
     *
     */
    private static final long serialVersionUID = 3556951750250328299L;

    public AnswerHttpException(final String msg) {
        super(msg);
    }

    public AnswerHttpException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}

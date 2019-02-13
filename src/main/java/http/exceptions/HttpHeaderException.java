package http.exceptions;

public class HttpHeaderException extends AnswerHttpException {

    /**
     *
     */
    private static final long serialVersionUID = 654050855603172515L;

    public HttpHeaderException(final String msg) {
        super(msg);
    }

    public HttpHeaderException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

}

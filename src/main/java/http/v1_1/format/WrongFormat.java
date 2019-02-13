package http.v1_1.format;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class WrongFormat extends Throwable {

    private static final long serialVersionUID = -1319807679534688174L;
    private final Pattern     offendingPattern;

    public WrongFormat(final String message, final WrongFormat cause) {
        super(message, cause);
        offendingPattern = null;
    }

    public WrongFormat(final String message, final Pattern offender) {
        super(message);
        offendingPattern = offender;
    }

    public Pattern getOffendingPattern() {
        return offendingPattern;
    }

    public List<WrongFormat> getCauseChain() {
        LinkedList<WrongFormat> result = new LinkedList<>();
        result.add(this);
        WrongFormat cause = this.getCause();
        while (cause != null) {
            result.addLast(cause);
            cause = cause.getCause();
        }
        return result;
    }

    public String getCauseChainString() {
        List<WrongFormat> causes = getCauseChain();
        StringJoiner joiner = new StringJoiner("<--", "", "\n");
        for (WrongFormat cause : causes) {
            joiner.add(cause.getClass().getSimpleName());
        }
        return joiner.toString();
    }

    @Override
    public synchronized WrongFormat getCause() {
        return (WrongFormat) super.getCause();
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName();
        if (this.offendingPattern != null) {
            result += " (Pattern \"" + offendingPattern.toString() + "\")";
        }
        String message = getMessage();
        if (message != null && !message.isEmpty()) {
            result += ": " + message;
        }
        return result;
    }

}

package http.url;

import java.util.regex.Pattern;

import http.MalformedArgumentException;
import lombok.Value;

/**
 *
 * Representation of the user information inside a URL.
 * <p>
 * <b>Example:</b> The URL
 * <p>
 * <code>http://Hans:Wurst@example.com/info.php?q=9</code>
 * </p>
 * <p>
 * has {@link #getName() username} "Hans" and {@link #getPassword() password}
 * "Wurst". Using this method of credential transmission is deprecated.
 * </p>
 * <p>
 * This class also exposes {@link #VALID_USERINFO}, a {@link Pattern} that
 * decides whether given username and password is valid.
 * </p>
 *
 * @see <a href="https://tools.ietf.org/html/rfc3986#section-3.2.1">RFC
 *      3986-3.2.1</a>
 *
 * @author Simon
 *
 */
@Value
public class User {

    private static final String PATTERN_ALPHANUMERIC = "[A-Za-z0-9]";
    private static final String PATTERN_PERC_ENCODED = "(%[0-9a-fA-F][0-9a-fA-F])";
    private static final String PATTERN_SUBDELIMITER = "[!$&'\\(\\)\\*\\+,;=]";

    public static final Pattern VALID_USERINFO = Pattern.compile("("
            + PATTERN_ALPHANUMERIC + "|"
            + PATTERN_PERC_ENCODED + "|"
            + PATTERN_SUBDELIMITER + ")*");

    public static class MalformedUserInfoException extends MalformedArgumentException {

        private static final long serialVersionUID = 7550492536976445627L;

        public MalformedUserInfoException(final String msg) {
            super(msg);
        }

        public MalformedUserInfoException(final String msg, final Throwable cause) {
            super(msg, cause);
        }
    }

    public User(final String name, final String password) throws MalformedUserInfoException {
        if (!VALID_USERINFO.matcher(name).matches()) {
            throw new MalformedUserInfoException(
                    "User name '" + name + "' is supposed to match RegEx("
                            + VALID_USERINFO.toString() + "), but it didn't.");
        }
        if (!VALID_USERINFO.matcher(password).matches()) {
            throw new MalformedUserInfoException(
                    "User pasword '" + password + "' is supposed to match RegEx("
                            + VALID_USERINFO.toString() + "), but it didn't.");
        }
        this.name = name;
        this.password = password;
    }

    String name;
    String password;
}

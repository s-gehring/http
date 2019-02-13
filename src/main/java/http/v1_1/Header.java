package http.v1_1;

import http.v1_1.format.Format;
import http.v1_1.format.HttpMessagePart;
import http.v1_1.format.RegexGroup;
import http.v1_1.format.WrongFormat;
import lombok.Getter;

@Getter
@Format(value = "(" + Patterns.TOKEN + "):(.+)")
public class Header extends HttpMessagePart {

    @RegexGroup(1)
    private String key;
    @RegexGroup(2)
    private String value;

    public Header(final String in) throws WrongFormat {
        super(in);
        value = value.trim();
    }

}

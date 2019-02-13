package http.v1_1;

import http.v1_1.format.Format;
import http.v1_1.format.HttpMessagePart;
import http.v1_1.format.RegexGroup;
import http.v1_1.format.WrongFormat;
import lombok.Getter;

@Getter
@Format("(.|\\r|\\r\\n|\\n)+")
public class Body extends HttpMessagePart {

    @RegexGroup(0)
    private String body;

    protected Body(final String value) throws WrongFormat {
        super(value);
    }

}

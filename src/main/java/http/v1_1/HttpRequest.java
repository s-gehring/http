package http.v1_1;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class HttpRequest extends HttpMessage {

    RequestLine  startLine;
    List<Header> headers;
    Body         body;

    @Override
    public Type type() {
        return Type.REQUEST;
    }

}

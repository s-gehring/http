package http.v1_1;

import java.util.List;

public abstract class HttpAnswer extends HttpMessage {

    StatusLine   statusLine;
    List<Header> headers;
    Body         body;

    @Override
    public Type type() {
        return Type.RESPONSE;
    }
}

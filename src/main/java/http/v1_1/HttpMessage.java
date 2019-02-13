package http.v1_1;

public abstract class HttpMessage {

    enum Type {
        REQUEST, RESPONSE
    }

    public abstract Type type();
}

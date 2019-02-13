package http;

import lombok.Value;

@Value
public class HttpVersion {

    public static final HttpVersion HTTP_1_0 = new HttpVersion("1.0");
    public static final HttpVersion HTTP_1_1 = new HttpVersion("1.1");
    public static final HttpVersion HTTP_2   = new HttpVersion("2");

    private String version;

}

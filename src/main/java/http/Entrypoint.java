package http;

import http.v1_1.Header;
import http.v1_1.format.WrongFormat;

public class Entrypoint {

    public static void main(final String[] args) throws Exception, WrongFormat {

        new Header("Content-Type: application/json");

    }

}

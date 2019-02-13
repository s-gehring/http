package http.v1_1;

import http.v1_1.format.Format;

@Format(Patterns.REQUEST_LINE +
        "(((" + Patterns.GENERAL_HEADER + ")|(" +
        Patterns.REQUEST_HEADER + ")|(" +
        Patterns.ENTITY_HEADER + "))" + Patterns.CRLF + ")*" +
        Patterns.CRLF + "(" +
        Patterns.MESSAGE_BODY + ")")
public class Request {

}

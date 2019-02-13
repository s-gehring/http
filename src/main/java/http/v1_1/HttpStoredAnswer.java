package http.v1_1;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HttpStoredAnswer extends HttpAnswer {

    private final List<Header> headers;
    private final Body         body;

}

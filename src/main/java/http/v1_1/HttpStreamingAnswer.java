package http.v1_1;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HttpStreamingAnswer extends HttpAnswer implements AutoCloseable {

    private final List<Header> headers;
    private final InputStream  bodyStream;

    @Override
    public void close() throws IOException {
        bodyStream.close();
    }

}

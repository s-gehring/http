// package http;
//
// import java.io.BufferedReader;
// import java.io.DataOutputStream;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.net.Socket;
// import java.net.URL;
// import java.net.UnknownHostException;
// import java.util.LinkedList;
// import java.util.StringJoiner;
//
// import http.answer.Headers;
// import http.exceptions.HttpHeaderException;
// import http.url.Fragment;
// import http.url.Hostname;
// import http.url.Hostname.MalformedHostnameException;
// import http.url.MalformedUrlException;
// import http.url.Path;
// import http.url.Port;
// import http.url.Query;
// import http.url.User;
// import http.url.User.MalformedUserInfoException;
// import http.v1_1.Body;
// import http.v1_1.Header;
// import http.v1_1.HttpAnswer;
// import http.v1_1.HttpStoredAnswer;
// import http.v1_1.HttpStreamingAnswer;
// import lombok.Getter;
// import lombok.Setter;
//
// @Deprecated
// @Getter
// @Setter
// public class CompleteRequest {
//
// private Method method;
// private Protocol protocol;
// private Hostname hostname;
// private Port port;
// private Path path;
// private Query query;
// private Fragment fragment;
// private HttpVersion httpVersion = HttpVersion.HTTP_1_1;
//
// private User user;
//
// private void setUserInfo(final URL url) {
// String userData = url.getUserInfo();
// if (userData != null && !userData.isEmpty()) {
// String[] userFragments = userData.split(":");
// if (userFragments.length != 2) {
// throw new MalformedUrlException("Failed to understand user info '" + userData
// + "'. Expected something matching '(.+:.+)'.");
// }
// try {
// user = new User(userFragments[0], userFragments[1]);
// } catch (MalformedUserInfoException exception) {
// throw new MalformedRequestException(
// "Request instantiation failed because the user info was wrong.",
// exception);
// }
// }
// }
//
// private String getHttpRequestAsString() {
// StringJoiner joiner = new StringJoiner("\n", "", "");
//
// joiner.add(method.name() + " " + path.getValue() + query.getValue() + " " +
// "HTTP/"
// + httpVersion.getVersion());
// joiner.add("Host: " + hostname.getValue());
// joiner.add("");
// joiner.add("");
//
// return joiner.toString();
// }
//
// private Headers getHeaders(final BufferedReader reader) {
// String line;
// Headers result = new Headers();
// LinkedList<String> headers = new LinkedList<>();
// try {
// while ((line = reader.readLine()) != null) {
// if (line.isEmpty()) {
// break;
// }
// headers.add(line);
// }
// } catch (IOException exception) {
// throw new HttpHeaderException("Failed to read header.", exception);
// }
// String header;
// while (!headers.isEmpty()) {
// header = headers.pop();
// String[] parts = header.split(":", 1);
// if (parts.length == 2) {
// String key = parts[0];
//
// // TODO: Find from spec if leading whitespaces belong to value.
// String value = parts[1].substring(1);
// result.add(new Header(key, value));
// } else {
// throw new HttpHeaderException("Header line '" + header + "' seems
// malformed.",
// new IllegalStateException(
// "Trying to split header string on ':' resulted in " + parts.length
// + " part(s) instead of expected 2."));
// }
// }
// return result;
// }
//
// public HttpAnswer send(final boolean readImmediately) {
//
// try (Socket clientSocket = new Socket(hostname.getValue(), port.getValue()))
// {
// InputStream serverAnswerStream = clientSocket.getInputStream();
//
// BufferedReader serverAnswerReader = new BufferedReader(
// new InputStreamReader(serverAnswerStream));
// DataOutputStream requestStream = new DataOutputStream(
// clientSocket.getOutputStream());
//
// serverAnswerReader.readLine();
// requestStream.writeBytes(getHttpRequestAsString());
//
// Headers headers = getHeaders(serverAnswerReader);
//
// if (readImmediately) {
// String body = StringUtil.readAll(serverAnswerReader);
// serverAnswerReader.close();
// return new HttpStoredAnswer(headers, new Body(body));
// }
// return new HttpStreamingAnswer(headers, serverAnswerStream);
//
// } catch (UnknownHostException exception) {
// exception.printStackTrace();
// } catch (IOException exception) {
// exception.printStackTrace();
// }
//
// return null;
// }
//
// public CompleteRequest(final URL url) {
// setUserInfo(url);
// try {
// hostname = new Hostname(url.getHost());
// } catch (MalformedHostnameException exception) {
// throw new MalformedRequestException(
// "Request instantiation failed because the hostname was wrong.", exception);
// }
// int portInt = url.getPort();
// if (portInt == -1) {
// portInt = url.getDefaultPort();
// }
// port = new Port(portInt);
// path = new Path(url.getPath());
// query = new Query(url.getQuery());
//
// }
//
// @Override
// public String toString() {
// return getHttpRequestAsString();
// }
//
// }

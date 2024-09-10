package org.apache.coyote.http11.domain.response;

import java.util.Map;
import org.apache.coyote.http11.domain.cookie.Cookie;

public class HttpResponse {

    private static final String LOCATION_HEADER_KEY = "location";

    private final Map<String, String> responseHeaders;
    private HttpStatus httpStatus;
    private String messageBody;

    HttpResponse(HttpStatus httpStatus, Map<String, String> responseHeaders, String messageBody) {
        this.httpStatus = httpStatus;
        this.responseHeaders = responseHeaders;
        this.messageBody = messageBody;
    }

    public static HttpResponseBuilder status(HttpStatus httpStatus) {
        return new HttpResponseBuilder().status(httpStatus);
    }

    public static HttpResponseBuilder redirect(String location) {
        return status(HttpStatus.FOUND).header(LOCATION_HEADER_KEY, location);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    public String getResponseHeader(String key) {
        return responseHeaders.get(key);
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String body) {
        this.messageBody = body;
        responseHeaders.put("Content-Length", String.valueOf(body.getBytes().length));
    }

    public void setStatus(HttpStatus status) {
        this.httpStatus = status;
    }

    public void setContentType(String contentType) {
        responseHeaders.put("Content-Type", contentType);
    }

    public void setRedirect(String location) {
        this.httpStatus = HttpStatus.FOUND;
        responseHeaders.put(LOCATION_HEADER_KEY, location);
    }

    public void setCookie(Cookie cookie) {
        responseHeaders.put("Set-Cookie", cookie.toCookieString());
    }
}

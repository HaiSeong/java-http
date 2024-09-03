package org.apache.coyote.http11;

public enum HttpMethod {
    GET,
    POST,
    HEAD,
    OPTIONS,
    PUT,
    DELETE,
    TRACE,
    CONNECT;

    public static HttpMethod ofName(String methodName) {
        try {
            return HttpMethod.valueOf(methodName);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid HTTP method: " + methodName);
        }
    }
}

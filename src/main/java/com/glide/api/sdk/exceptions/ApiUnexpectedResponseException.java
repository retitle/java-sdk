package com.glide.api.sdk.exceptions;

import java.util.List;
import java.util.Map;

public class ApiUnexpectedResponseException extends ApiAuthException {
    public ApiUnexpectedResponseException(String message, Throwable cause, int statusCode, Map<String, List<String>> responseHeaders, Object params) {
        super(message, cause, statusCode, responseHeaders, params);
    }

    public ApiUnexpectedResponseException(String message, int statusCode, Map<String, List<String>> responseHeaders, Object params) {
        super(message, statusCode, responseHeaders, params);
    }
}

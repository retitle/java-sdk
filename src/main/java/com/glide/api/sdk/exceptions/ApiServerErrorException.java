package com.glide.api.sdk.exceptions;

import java.util.List;
import java.util.Map;

public class ApiServerErrorException extends ApiException {
    public ApiServerErrorException(String message, Throwable cause, int statusCode, Map<String, List<String>> responseHeaders, Object params) {
        super(message, cause, statusCode, responseHeaders, params);
    }

    public ApiServerErrorException(String message, int statusCode, Map<String, List<String>> responseHeaders, Object params) {
        super(message, statusCode, responseHeaders, params);
    }
}

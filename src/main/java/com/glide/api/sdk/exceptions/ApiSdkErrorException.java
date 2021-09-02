package com.glide.api.sdk.exceptions;

import java.util.List;
import java.util.Map;

public class ApiSdkErrorException extends ApiException {
    public ApiSdkErrorException(String message, Throwable cause, int statusCode, Map<String, List<String>> responseHeaders, Object params) {
        super(message, cause, statusCode, responseHeaders, params);
    }

    public ApiSdkErrorException(String message, int statusCode, Map<String, List<String>> responseHeaders, Object params) {
        super(message, statusCode, responseHeaders, params);
    }
}

package com.glide.api.sdk.exceptions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.google.gson.JsonSyntaxException;
import com.glide.api.sdk.utils.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ApiException extends Exception {
    private int statusCode;
    private Map<String, List<String>> responseHeaders;
    private Object params;

    private static final Map<Integer, Class<? extends ApiException>> exceptionClassByStatusCode = new HashMap<Integer, Class<? extends ApiException>>() {{
        put(400, ApiBadRequestException.class);
        put(401, ApiUnauthorizedException.class);
        put(403, ApiForbiddenException.class);
        put(404, ApiNotFoundException.class);
        put(500, ApiInternalErrorException.class);
    }};

    @Data
    @AllArgsConstructor
    private static class ErrorModel {
        Class<? extends ApiException> cls;
        private String message;
        private Object params;
    }

    public static void throwException(Throwable cause, int statusCode, String responseBody, Map<String, List<String>> responseHeaders) throws ApiException {
        try {
            Class<? extends ApiException> cls;
            if (exceptionClassByStatusCode.containsKey(statusCode)) {
                cls = exceptionClassByStatusCode.get(statusCode);
            } else if (statusCode <= -1) {
                cls = ApiUnreachableException.class;
            } else if (statusCode > 500) {
                cls = ApiServerErrorException.class;
            } else if (statusCode >= 400 && statusCode < 500) {
                cls = ApiClientErrorException.class;
            } else {
                cls = ApiUnexpectedResponseException.class;
            }

            ErrorModel error = new ErrorModel(cls, "", null);

            System.out.println(responseBody);

            if (responseBody != null && responseBody != "") {
                try {
                    error = Utils.getGson().fromJson(responseBody, ErrorModel.class);
                    error.setCls(cls);
                } catch (JsonSyntaxException err) {
                    error = new ErrorModel(ApiUnexpectedResponseException.class, "Unexpected response", err);
                }
            }

            Constructor<? extends ApiException> constructor;
            ApiException exception;

            if (cause != null) {
                constructor = error.getCls().getConstructor(String.class, Throwable.class, int.class, Map.class, Object.class);
                exception = (ApiException)constructor.newInstance(error.message, cause, statusCode, responseHeaders, error.params);
            } else {
                constructor = error.getCls().getConstructor(String.class, int.class, Map.class, Object.class);
                exception = (ApiException)constructor.newInstance(error.message, statusCode, responseHeaders, error.params);
            }
            throw exception;
        } catch (NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {
            throw new ApiSdkErrorException("Something went wrokng with the SDK.", e, -1, null, null);
        }
    }

    public static void throwException(int statusCode, String responseBody, Map<String, List<String>> responseHeaders) throws ApiException {
        throwException(null, statusCode, responseBody, responseHeaders);
    }

    public ApiException(String message, Throwable cause, int statusCode, Map<String, List<String>> responseHeaders, Object params) {
        super(message, cause);
        this.statusCode = statusCode;
        this.responseHeaders = responseHeaders;
        this.params = params;
    }

    public ApiException(String message, int statusCode, Map<String, List<String>> responseHeaders, Object params) {
        super(message);
        this.statusCode = statusCode;
        this.responseHeaders = responseHeaders;
        this.params = params;
    }

    public ApiException(Throwable cause, int statusCode, Map<String, List<String>> responseHeaders, Object params) {
        super(cause);
        this.statusCode = statusCode;
        this.responseHeaders = responseHeaders;
        this.params = params;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return responseHeaders;
    }

    public Object getParams() {
        return params;
    }
}

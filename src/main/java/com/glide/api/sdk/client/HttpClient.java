package com.glide.api.sdk.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
public class HttpClient {
    @Data
    @NoArgsConstructor
    public static class Response {
        private boolean connected = false;
        private IOException error;
        private Map<String, List<String>> headers;
        private int statusCode = -1;
        private String body;

        public boolean isError() {
            return statusCode >= 400;
        }

        public boolean isOk() {
            return !isError() && statusCode >= 200 && statusCode < 300;
        }
    }

    public static class Headers {
        Map<String, Object> headers;

        public static Headers make(Map<String, String> headers) {
            Map<String, Object> headersVal = new HashMap<String, Object>();
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                headersVal.put(entry.getKey(), (Object)entry.getValue());
            }
            return new Headers(headersVal);
        }

        public Headers() {
            this.headers = new HashMap<String, Object>();
        }

        public Headers(Map<String, Object> headers) {
            this.headers = headers;
        }

        public void setHeader(String name, Object value) {
            headers.put(name, value);
        }

        public void setHeader(String name, String value) {
            setHeader(name, (Object)value);
        }

        public void setHeader(String name, List<String> value) {
            setHeader(name, (Object)value);
        }

        public String getHeader(String name) {
            return (String)headers.get(name);
        }

        @SuppressWarnings("unchecked")
        public List<String> getListHeader(String name) {
            return (List<String>)headers.get(name);
        }

        @SuppressWarnings("unchecked")
        public void set(HttpURLConnection conn) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                String rawHeaderValue = entry.getValue() instanceof String ? (String)entry.getValue() : String.join(",", (List<String>)entry.getValue());
                conn.setRequestProperty(entry.getKey(), rawHeaderValue);
            }
        }

        public String toString() {
            String s = "";
            for (String h : headers.keySet()) {
                s += h + " - " + headers.get(h) + "\n";
            }
            return s;
        }
    }

    public static class QueryParams {
        Map<String, QParam> params;

        public QueryParams() {
            this.params = new HashMap<String, QParam>();
        }

        @Getter
        @Setter
        private static abstract class QParam {
            protected String name;

            public abstract String getValue();
            public abstract boolean isList();

            private String getEncodedName() {
                try {
                    return URLEncoder.encode(getName(), Charset.defaultCharset().toString());
                } catch (UnsupportedEncodingException err) {
                    // Unreachable
                    return "";
                }
            }

            private String getEncodedValue() {
                try {
                    return URLEncoder.encode(getValue(), Charset.defaultCharset().toString());
                } catch (UnsupportedEncodingException err) {
                    // Unreachable
                    return "";
                }
            }

            public String getEncodedStr(boolean first) {
                if (getValue() == null) {
                    return "";
                }
                return (first ? "" : "&") + getEncodedName() + "=" + getEncodedValue();
            }
        }

        @Data
        @EqualsAndHashCode(callSuper=false)
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Param extends QParam {
            private String value;

            public Param(@NonNull String name, String value) {
                this.name = name;
                this.value = value;
            }

            public boolean isList() {
                return false;
            }
        }

        @Data
        @EqualsAndHashCode(callSuper=false)
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ListParam extends QParam {
            private List<String> listValue;

            public ListParam(@NonNull String name, List<String> listValue) {
                this.name = name;
                this.listValue = listValue;
            }

            public String getValue() {
                if (listValue == null) {
                    return null;
                }
                return String.join(",", listValue);
            }

            public boolean isList() {
                return true;
            }
        }

        public void add(QParam param) {
            if (param != null) {
                params.put(param.getName(), param);
            }
        }

        public void add(QueryParams qParams) {
            if (qParams != null) {
                for (QParam qParam : qParams.params.values()) {
                    add(qParam);
                }
            }
        }

        public QParam remove(String name) {
            return params.remove(name);
        }

        public boolean hasValues() {
            if (params == null) {
                return false;
            }

            for (QParam param : params.values()) {
                if (param.getValue() != null) {
                    return true;
                }
            }

            return false;
        }

        public String getEncodedStr() {
            if (!hasValues()) {
                return "";
            }

            String qParams = "?";
            boolean first = false;

            for (QParam param : params.values()) {
                String encodedParam = param.getEncodedStr(first);
                if (encodedParam != "") {
                    qParams += encodedParam;
                    first = false;
                }
            }

            return qParams;
        }
    }

    public Response request(String requestURL, String requestMethod, Headers headers, QueryParams qParams, String payload, String contentType)
    {
        Response res = new Response();
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(requestURL + (qParams != null ? qParams.getEncodedStr() : "")).openConnection();
            conn.setRequestProperty("Accept", "application/json");
            headers.set(conn);
            if (requestMethod != "GET") {
                conn.setRequestMethod("POST");
                if (payload != null) {
                    conn.setRequestProperty("Content-Type", contentType != null ? contentType : "application/json");
                    conn.setDoOutput(true);
                    OutputStream outputStream = conn.getOutputStream();
                    byte[] input = payload.getBytes("utf-8");
                    outputStream.write(input, 0, input.length);
                    outputStream.close();
                }
            }
            res.setHeaders(conn.getHeaderFields());
            res.setStatusCode(conn.getResponseCode());
            res.setConnected(true);
            InputStream stream = !res.isError() ? conn.getInputStream() : conn.getErrorStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(stream, "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            res.setBody(response.toString());
            stream.close();

        } catch (IOException err) {
            res.setError(err);
        }
        return res;
    }

    public Response get(String requestURL, Headers headers, QueryParams qParams) {
        return request(requestURL, "GET", headers, qParams, null, null);
    }

    public Response get(String requestURL, Headers headers) {
        return request(requestURL, "GET", headers, null, null, null);
    }

    public Response get(String requestURL, QueryParams qParams) {
        return request(requestURL, "GET", null, qParams, null, null);
    }

    public Response get(String requestURL) {
        return get(requestURL, null, null);
    }

    public Response post(String requestURL, Headers headers, QueryParams qParams, String payload, String contentType) {
        return request(requestURL, "POST", headers, qParams, payload, contentType);
    }

    public Response post(String requestURL, Headers headers, QueryParams qParams, String payload) {
        return post(requestURL, headers, qParams, payload, payload != null && payload != "" ? "application/json" : null);
    }

    public Response post(String requestURL, Headers headers, String payload) {
        return post(requestURL, headers, null, payload);
    }

    public Response post(String requestURL, Headers headers) {
        return post(requestURL, headers, null);
    }

    public Response post(String requestURL, String payload) {
        return post(requestURL, null, payload);
    }

    public Response post(String requestURL) {
        return post(requestURL, (String)null);
    }
}

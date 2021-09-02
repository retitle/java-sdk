package com.glide.api.sdk.resources;

import com.glide.api.sdk.IGlideApiClient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Resource {
    private IGlideApiClient client;
    private String name;
    private String baseUrl;

    protected String getDetailUrl() {
        return baseUrl + "/%s";
    }

    protected String getListUrl() {
        return baseUrl;
    }

    protected String getMultiUrl() {
        return getListUrl();
    }

    protected String getCustomEndpointUrl(String name) {
        String base = baseUrl + "/%s";
        if (name == null || name== "") {
            return base;
        }
        return base + "/" + name;
    }

    protected String getCustomListEndpointUrl(String name) {
        if (name == null || name== "") {
            return baseUrl;
        }
        return baseUrl + "/" + name;
    }
}

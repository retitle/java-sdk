package com.glide.api.sdk.models.responses;

import lombok.Data;

@Data
public abstract class ResponseModel {
    private String object;

    private String defaultObjectName() {
        String fullClsName = getClass().getName();
        String[] parts = fullClsName.split("\\.");
        String clsName = parts[parts.length - 1];
        return clsName.substring(0, 1).toLowerCase() + clsName.substring(1);
    }


    public String getId() {
        return "";
    };

    public String getObject() {
        return object != null ? object : defaultObjectName();
    }

    public boolean isEmpty() {
        return getObject() == "empty";
    }

    public boolean isRef() {
        return getObject().startsWith("/ref/");
    }
}

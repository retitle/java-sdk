package com.glide.api.sdk.models.requests;

import com.glide.api.sdk.utils.Utils;

public abstract class RequestModel {
    public String getSerialized() {
        return Utils.getGson().toJson(this);
    }
}

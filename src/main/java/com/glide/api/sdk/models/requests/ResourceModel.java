package com.glide.api.sdk.models.requests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.glide.api.sdk.client.HttpClient.QueryParams;

import lombok.Data;

@Data
public abstract class ResourceModel {
    private String[] expand;

    private List<String> getExpandClean() {
        if (expand == null) {
            return new ArrayList<String>();
        }
        return Arrays.stream(expand).filter(v -> v != null && v != "").collect(Collectors.toList());
    }

    private QueryParams.ListParam getExpandQParam() {
        List<String> expandClean = getExpandClean();
        if (expandClean.isEmpty()) {
            return null;
        }
        return new QueryParams.ListParam("expand", expandClean);
    }

    public QueryParams getQueryParams() {
        return new QueryParams(){{ add(getExpandQParam()); }};
    }
}

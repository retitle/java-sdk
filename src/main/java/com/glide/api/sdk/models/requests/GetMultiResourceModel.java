package com.glide.api.sdk.models.requests;

import java.util.Arrays;

import com.glide.api.sdk.client.HttpClient.QueryParams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class GetMultiResourceModel extends ResourceModel {
    @NonNull private String[] ids;

    public GetMultiResourceModel(@NonNull String[] ids, String[] expand) {
        this.ids = ids;
        this.setExpand(expand);
    }

    public QueryParams getQueryParams() {
        QueryParams qParams = super.getQueryParams();
        qParams.add(new QueryParams.ListParam("ids", Arrays.asList(ids)));
        return qParams;
    }
}

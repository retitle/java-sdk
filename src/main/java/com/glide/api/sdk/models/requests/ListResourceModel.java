package com.glide.api.sdk.models.requests;

import com.glide.api.sdk.client.HttpClient.QueryParams;
import com.glide.api.sdk.models.responses.PageParams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class ListResourceModel extends ResourceModel {
    private PageParams pageParams;

    public ListResourceModel(String[] expand) {
        this.setExpand(expand);
    }

    public ListResourceModel(PageParams pageParams, String[] expand) {
        this.pageParams = pageParams;
        this.setExpand(expand);
    }

    public QueryParams getQueryParams() {
        QueryParams qParams = super.getQueryParams();
        if (pageParams != null) {
            qParams.add(pageParams.getQueryParams());
        }

        return qParams;
    }
}

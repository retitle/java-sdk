package com.glide.api.sdk.models.responses;

import com.glide.api.sdk.client.HttpClient.QueryParams;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParams {
    private String startingAfter;
    private int pageSize;

    public PageParams(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageParams(String startingAfter) {
        this.startingAfter = startingAfter;
    }

    private QueryParams.Param getStartingAfterQParam() {
        if (startingAfter == null || startingAfter == "") {
            return null;
        }
        return new QueryParams.Param("starting_after", startingAfter);
    }

    private QueryParams.Param getPageSizeQParam() {
        if (pageSize == 0) {
            return null;
        }
        return new QueryParams.Param("limit", String.valueOf(pageSize));
    }

    public QueryParams getQueryParams() {
        return new QueryParams() {{
            add(getStartingAfterQParam());
            add(getPageSizeQParam());
        }};
    }
}

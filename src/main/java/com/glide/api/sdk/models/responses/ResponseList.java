package com.glide.api.sdk.models.responses;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.AccessLevel;

@Data
@EqualsAndHashCode(callSuper=false)
public abstract class ResponseList<T extends ResponseModel> extends ResponseModel {
    private List<T> data;
    private String listObject;
    @Getter(AccessLevel.NONE)
	private boolean hasMore;

    public Boolean getHasMore() {
        return hasMore;
    }

    public PageParams nextPageParams(int newPageSize) {
        if (!hasMore) {
            return null;
        }

        int pageSize = data.size();
        int effectivePageSize = newPageSize > 0 ? newPageSize : pageSize;
        return new PageParams(data.get(pageSize - 1).getId(), effectivePageSize);
    }

    public PageParams nextPageParams() {
        return nextPageParams(0);
    }
}

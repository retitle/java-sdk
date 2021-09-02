package com.glide.api.sdk.models.requests;

import com.glide.api.sdk.models.responses.PageParams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class DetailResourceModel extends ResourceModel {
    @NonNull private String id;

    public DetailResourceModel(@NonNull String id, String[] expand) {
        this.id = id;
        this.setExpand(expand);
    }
}

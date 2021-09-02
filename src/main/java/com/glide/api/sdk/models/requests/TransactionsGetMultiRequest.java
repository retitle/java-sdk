package com.glide.api.sdk.models.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class TransactionsGetMultiRequest extends GetMultiResourceModel {
    public TransactionsGetMultiRequest (@NonNull String[] ids) {
        this.setIds(ids);
    }
}
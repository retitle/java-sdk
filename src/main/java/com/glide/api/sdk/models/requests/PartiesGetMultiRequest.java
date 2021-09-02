package com.glide.api.sdk.models.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PartiesGetMultiRequest extends GetMultiResourceModel {
    @NonNull private String transactionId;
    public PartiesGetMultiRequest (@NonNull String transactionId, @NonNull String[] ids) {
        this.setTransactionId(transactionId);
        this.setIds(ids);
    }
}
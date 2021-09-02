package com.glide.api.sdk.models.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PartiesListRequest extends ListResourceModel {
    @NonNull private String transactionId;
    public PartiesListRequest (@NonNull String transactionId) {
        this.setTransactionId(transactionId);
    }
}
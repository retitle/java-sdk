package com.glide.api.sdk.models.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PartiesDetailRequest extends DetailResourceModel {
    @NonNull private String transactionId;
    public PartiesDetailRequest (@NonNull String transactionId, @NonNull String id) {
        this.setTransactionId(transactionId);
        this.setId(id);
    }
}
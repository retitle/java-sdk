package com.glide.api.sdk.models.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class FoldersListRequest extends ListResourceModel {
    @NonNull private String transactionId;
    public FoldersListRequest (@NonNull String transactionId) {
        this.setTransactionId(transactionId);
    }
}
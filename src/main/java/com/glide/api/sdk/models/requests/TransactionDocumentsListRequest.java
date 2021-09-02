package com.glide.api.sdk.models.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class TransactionDocumentsListRequest extends ListResourceModel {
    @NonNull private String transactionId;
    @NonNull private String folderId;
    public TransactionDocumentsListRequest (@NonNull String transactionId, @NonNull String folderId) {
        this.setTransactionId(transactionId);
        this.setFolderId(folderId);
    }
}
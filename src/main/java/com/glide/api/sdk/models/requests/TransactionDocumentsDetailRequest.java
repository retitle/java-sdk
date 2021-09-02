package com.glide.api.sdk.models.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class TransactionDocumentsDetailRequest extends DetailResourceModel {
    @NonNull private String transactionId;
    @NonNull private String folderId;
    public TransactionDocumentsDetailRequest (@NonNull String transactionId, @NonNull String folderId, @NonNull String id) {
        this.setTransactionId(transactionId);
        this.setFolderId(folderId);
        this.setId(id);
    }
}
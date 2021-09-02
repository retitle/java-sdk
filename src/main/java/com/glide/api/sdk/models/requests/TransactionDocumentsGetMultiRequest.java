package com.glide.api.sdk.models.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class TransactionDocumentsGetMultiRequest extends GetMultiResourceModel {
    @NonNull private String transactionId;
    @NonNull private String folderId;
    public TransactionDocumentsGetMultiRequest (@NonNull String transactionId, @NonNull String folderId, @NonNull String[] ids) {
        this.setTransactionId(transactionId);
        this.setFolderId(folderId);
        this.setIds(ids);
    }
}
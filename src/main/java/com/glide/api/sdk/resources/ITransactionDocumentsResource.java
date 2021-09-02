package com.glide.api.sdk.resources;

import com.glide.api.sdk.exceptions.ApiException;
import com.glide.api.sdk.models.requests.*;
import com.glide.api.sdk.models.responses.*;

import lombok.NonNull;

public interface ITransactionDocumentsResource {

    public TransactionDocument getDetail(@NonNull TransactionDocumentsDetailRequest params) throws ApiException;

    public TransactionDocument getDetail(@NonNull String transactionId, @NonNull String folderId, @NonNull String id) throws ApiException;

    public TransactionDocumentList getMulti(@NonNull TransactionDocumentsGetMultiRequest params) throws ApiException;

    public TransactionDocumentList getMulti(@NonNull String transactionId, @NonNull String folderId, @NonNull String[] ids) throws ApiException;

    public TransactionDocumentList list(@NonNull TransactionDocumentsListRequest params) throws ApiException;

    public TransactionDocumentList list(@NonNull String transactionId, @NonNull String folderId) throws ApiException;
}

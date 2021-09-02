package com.glide.api.sdk.resources;

import com.glide.api.sdk.IGlideApiClient;
import com.glide.api.sdk.exceptions.ApiException;
import com.glide.api.sdk.models.requests.*;
import com.glide.api.sdk.models.responses.*;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class TransactionDocumentsResource extends Resource implements ITransactionDocumentsResource {

    public TransactionDocumentsResource(IGlideApiClient client) {
        super(client, "transaction_documents", "/transactions/%s/folders/%s/transaction_documents");
    }

    public TransactionDocument getDetail(@NonNull TransactionDocumentsDetailRequest params) throws ApiException {
        return (TransactionDocument)getClient().getWithAuth(TransactionDocument.class, String.format(getDetailUrl(), params.getTransactionId(), params.getFolderId(), params.getId()), params.getQueryParams());
    }

    public TransactionDocument getDetail(@NonNull String transactionId, @NonNull String folderId, @NonNull String id) throws ApiException {
        return getDetail(new TransactionDocumentsDetailRequest(transactionId, folderId, id));
    }

    public TransactionDocumentList getMulti(@NonNull TransactionDocumentsGetMultiRequest params) throws ApiException  {
        return (TransactionDocumentList)getClient().getWithAuth(TransactionDocumentList.class, String.format(getMultiUrl(), params.getTransactionId(), params.getFolderId()), params.getQueryParams());
    }

    public TransactionDocumentList getMulti(@NonNull String transactionId, @NonNull String folderId, @NonNull String[] ids) throws ApiException {
        return getMulti(new TransactionDocumentsGetMultiRequest(transactionId, folderId, ids));
    }

    public TransactionDocumentList list(@NonNull TransactionDocumentsListRequest params) throws ApiException {
        return (TransactionDocumentList)getClient().getWithAuth(TransactionDocumentList.class, String.format(getListUrl(), params.getTransactionId(), params.getFolderId()), params.getQueryParams());
    }

    public TransactionDocumentList list(@NonNull String transactionId, @NonNull String folderId) throws ApiException {
        return list(new TransactionDocumentsListRequest(transactionId, folderId));
    }
}

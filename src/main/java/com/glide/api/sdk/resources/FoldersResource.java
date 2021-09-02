package com.glide.api.sdk.resources;

import com.glide.api.sdk.IGlideApiClient;
import com.glide.api.sdk.exceptions.ApiException;
import com.glide.api.sdk.models.requests.*;
import com.glide.api.sdk.models.responses.*;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class FoldersResource extends Resource implements IFoldersResource {
    @Getter @Setter
    private ITransactionDocumentsResource transactionDocuments;

    public FoldersResource(IGlideApiClient client) {
        super(client, "folders", "/transactions/%s/folders");
        this.setTransactionDocuments(new TransactionDocumentsResource(client));
    }

    public Folder getDetail(@NonNull FoldersDetailRequest params) throws ApiException {
        return (Folder)getClient().getWithAuth(Folder.class, String.format(getDetailUrl(), params.getTransactionId(), params.getId()), params.getQueryParams());
    }

    public Folder getDetail(@NonNull String transactionId, @NonNull String id) throws ApiException {
        return getDetail(new FoldersDetailRequest(transactionId, id));
    }

    public FolderList getMulti(@NonNull FoldersGetMultiRequest params) throws ApiException  {
        return (FolderList)getClient().getWithAuth(FolderList.class, String.format(getMultiUrl(), params.getTransactionId()), params.getQueryParams());
    }

    public FolderList getMulti(@NonNull String transactionId, @NonNull String[] ids) throws ApiException {
        return getMulti(new FoldersGetMultiRequest(transactionId, ids));
    }

    public FolderList list(@NonNull FoldersListRequest params) throws ApiException {
        return (FolderList)getClient().getWithAuth(FolderList.class, String.format(getListUrl(), params.getTransactionId()), params.getQueryParams());
    }

    public FolderList list(@NonNull String transactionId) throws ApiException {
        return list(new FoldersListRequest(transactionId));
    }

    public UploadsResponse transactionDocuments(@NonNull String transactionId, @NonNull String folderId, @NonNull TransactionDocumentUploads transactionDocumentUploads) throws ApiException  {
        return (UploadsResponse)getClient().postWithAuth(UploadsResponse.class, String.format(getCustomListEndpointUrl("transaction_documents"), transactionId, folderId), transactionDocumentUploads);
    }
}

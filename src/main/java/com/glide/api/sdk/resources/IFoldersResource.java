package com.glide.api.sdk.resources;

import com.glide.api.sdk.exceptions.ApiException;
import com.glide.api.sdk.models.requests.*;
import com.glide.api.sdk.models.responses.*;

import lombok.NonNull;

public interface IFoldersResource {
    public ITransactionDocumentsResource getTransactionDocuments ();
    public void setTransactionDocuments (ITransactionDocumentsResource transactionDocuments);

    public Folder getDetail(@NonNull FoldersDetailRequest params) throws ApiException;

    public Folder getDetail(@NonNull String transactionId, @NonNull String id) throws ApiException;

    public FolderList getMulti(@NonNull FoldersGetMultiRequest params) throws ApiException;

    public FolderList getMulti(@NonNull String transactionId, @NonNull String[] ids) throws ApiException;

    public FolderList list(@NonNull FoldersListRequest params) throws ApiException;

    public FolderList list(@NonNull String transactionId) throws ApiException;

    public UploadsResponse transactionDocuments(@NonNull String transactionId, @NonNull String folderId, @NonNull TransactionDocumentUploads transactionDocumentUploads) throws ApiException;
}

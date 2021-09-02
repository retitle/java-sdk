package com.glide.api.sdk.resources;

import com.glide.api.sdk.exceptions.ApiException;
import com.glide.api.sdk.models.requests.*;
import com.glide.api.sdk.models.responses.*;

import lombok.NonNull;

public interface ITransactionsResource {
    public IFoldersResource getFolders ();
    public void setFolders (IFoldersResource folders);
    public IPartiesResource getParties ();
    public void setParties (IPartiesResource parties);
    public ITransactionDocumentsResource getTransactionDocuments ();
    public void setTransactionDocuments (ITransactionDocumentsResource transactionDocuments);

    public Transaction getDetail(@NonNull TransactionsDetailRequest params) throws ApiException;

    public Transaction getDetail(@NonNull String id) throws ApiException;

    public TransactionList getMulti(@NonNull TransactionsGetMultiRequest params) throws ApiException;

    public TransactionList getMulti(@NonNull String[] ids) throws ApiException;

    public TransactionList list(@NonNull TransactionsListRequest params) throws ApiException;

    public TransactionList list() throws ApiException;

    public CreateResponse create(@NonNull TransactionCreate transactionCreate) throws ApiException;

    public FieldsResponse fields(@NonNull String id, @NonNull FieldWriteDict fieldWriteDict) throws ApiException;

    public FolderCreatesResponse folderCreates(@NonNull String id, @NonNull FolderCreates folderCreates) throws ApiException;

    public FolderRenamesResponse folderRenames(@NonNull String id, @NonNull FolderRenames folderRenames) throws ApiException;

    public ItemDeletesResponse itemDeletes(@NonNull String id, @NonNull ItemDeletes itemDeletes) throws ApiException;

    public PartyCreatesResponse partyCreates(@NonNull String id, @NonNull PartyCreates partyCreates) throws ApiException;

    public PartyPatchesResponse partyPatches(@NonNull String id, @NonNull PartyPatches partyPatches) throws ApiException;

    public PartyRemovesResponse partyRemoves(@NonNull String id, @NonNull PartyRemoves partyRemoves) throws ApiException;

    public TransactionDocumentAssignmentsResponse transactionDocumentAssignments(@NonNull String id, @NonNull TransactionDocumentAssignments transactionDocumentAssignments) throws ApiException;

    public TransactionDocumentRenamesResponse transactionDocumentRenames(@NonNull String id, @NonNull TransactionDocumentRenames transactionDocumentRenames) throws ApiException;

    public TransactionDocumentRestoresResponse transactionDocumentRestores(@NonNull String id, @NonNull TransactionDocumentsRestores transactionDocumentsRestores) throws ApiException;

    public TransactionDocumentTrashesResponse transactionDocumentTrashes(@NonNull String id, @NonNull TransactionDocumentTrashes transactionDocumentTrashes) throws ApiException;

    public UploadsResponse transactionDocuments(@NonNull String transactionId, @NonNull TransactionDocumentUploads transactionDocumentUploads) throws ApiException;
}

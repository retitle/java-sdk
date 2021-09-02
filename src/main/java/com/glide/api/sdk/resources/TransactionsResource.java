package com.glide.api.sdk.resources;

import com.glide.api.sdk.IGlideApiClient;
import com.glide.api.sdk.exceptions.ApiException;
import com.glide.api.sdk.models.requests.*;
import com.glide.api.sdk.models.responses.*;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class TransactionsResource extends Resource implements ITransactionsResource {
    @Getter @Setter
    private IFoldersResource folders;
    @Getter @Setter
    private IPartiesResource parties;
    @Getter @Setter
    private ITransactionDocumentsResource transactionDocuments;

    public TransactionsResource(IGlideApiClient client) {
        super(client, "transactions", "/transactions");
        this.setFolders(new FoldersResource(client));
        this.setParties(new PartiesResource(client));
        this.setTransactionDocuments(new TransactionDocumentsResource(client));
    }

    public Transaction getDetail(@NonNull TransactionsDetailRequest params) throws ApiException {
        return (Transaction)getClient().getWithAuth(Transaction.class, String.format(getDetailUrl(), params.getId()), params.getQueryParams());
    }

    public Transaction getDetail(@NonNull String id) throws ApiException {
        return getDetail(new TransactionsDetailRequest(id));
    }

    public TransactionList getMulti(@NonNull TransactionsGetMultiRequest params) throws ApiException  {
        return (TransactionList)getClient().getWithAuth(TransactionList.class, String.format(getMultiUrl()), params.getQueryParams());
    }

    public TransactionList getMulti(@NonNull String[] ids) throws ApiException {
        return getMulti(new TransactionsGetMultiRequest(ids));
    }

    public TransactionList list(@NonNull TransactionsListRequest params) throws ApiException {
        return (TransactionList)getClient().getWithAuth(TransactionList.class, String.format(getListUrl()), params.getQueryParams());
    }

    public TransactionList list() throws ApiException {
        return list(new TransactionsListRequest());
    }

    public CreateResponse create(@NonNull TransactionCreate transactionCreate) throws ApiException  {
        return (CreateResponse)getClient().postWithAuth(CreateResponse.class, String.format(getCustomListEndpointUrl("")), transactionCreate);
    }

    public FieldsResponse fields(@NonNull String id, @NonNull FieldWriteDict fieldWriteDict) throws ApiException  {
        return (FieldsResponse)getClient().postWithAuth(FieldsResponse.class, String.format(getCustomEndpointUrl("fields"), id), fieldWriteDict);
    }

    public FolderCreatesResponse folderCreates(@NonNull String id, @NonNull FolderCreates folderCreates) throws ApiException  {
        return (FolderCreatesResponse)getClient().postWithAuth(FolderCreatesResponse.class, String.format(getCustomEndpointUrl("folder_creates"), id), folderCreates);
    }

    public FolderRenamesResponse folderRenames(@NonNull String id, @NonNull FolderRenames folderRenames) throws ApiException  {
        return (FolderRenamesResponse)getClient().postWithAuth(FolderRenamesResponse.class, String.format(getCustomEndpointUrl("folder_renames"), id), folderRenames);
    }

    public ItemDeletesResponse itemDeletes(@NonNull String id, @NonNull ItemDeletes itemDeletes) throws ApiException  {
        return (ItemDeletesResponse)getClient().postWithAuth(ItemDeletesResponse.class, String.format(getCustomEndpointUrl("item_deletes"), id), itemDeletes);
    }

    public PartyCreatesResponse partyCreates(@NonNull String id, @NonNull PartyCreates partyCreates) throws ApiException  {
        return (PartyCreatesResponse)getClient().postWithAuth(PartyCreatesResponse.class, String.format(getCustomEndpointUrl("party_creates"), id), partyCreates);
    }

    public PartyPatchesResponse partyPatches(@NonNull String id, @NonNull PartyPatches partyPatches) throws ApiException  {
        return (PartyPatchesResponse)getClient().postWithAuth(PartyPatchesResponse.class, String.format(getCustomEndpointUrl("party_patches"), id), partyPatches);
    }

    public PartyRemovesResponse partyRemoves(@NonNull String id, @NonNull PartyRemoves partyRemoves) throws ApiException  {
        return (PartyRemovesResponse)getClient().postWithAuth(PartyRemovesResponse.class, String.format(getCustomEndpointUrl("party_removes"), id), partyRemoves);
    }

    public TransactionDocumentAssignmentsResponse transactionDocumentAssignments(@NonNull String id, @NonNull TransactionDocumentAssignments transactionDocumentAssignments) throws ApiException  {
        return (TransactionDocumentAssignmentsResponse)getClient().postWithAuth(TransactionDocumentAssignmentsResponse.class, String.format(getCustomEndpointUrl("transaction_document_assignments"), id), transactionDocumentAssignments);
    }

    public TransactionDocumentRenamesResponse transactionDocumentRenames(@NonNull String id, @NonNull TransactionDocumentRenames transactionDocumentRenames) throws ApiException  {
        return (TransactionDocumentRenamesResponse)getClient().postWithAuth(TransactionDocumentRenamesResponse.class, String.format(getCustomEndpointUrl("transaction_document_renames"), id), transactionDocumentRenames);
    }

    public TransactionDocumentRestoresResponse transactionDocumentRestores(@NonNull String id, @NonNull TransactionDocumentsRestores transactionDocumentsRestores) throws ApiException  {
        return (TransactionDocumentRestoresResponse)getClient().postWithAuth(TransactionDocumentRestoresResponse.class, String.format(getCustomEndpointUrl("transaction_document_restores"), id), transactionDocumentsRestores);
    }

    public TransactionDocumentTrashesResponse transactionDocumentTrashes(@NonNull String id, @NonNull TransactionDocumentTrashes transactionDocumentTrashes) throws ApiException  {
        return (TransactionDocumentTrashesResponse)getClient().postWithAuth(TransactionDocumentTrashesResponse.class, String.format(getCustomEndpointUrl("transaction_document_trashes"), id), transactionDocumentTrashes);
    }

    public UploadsResponse transactionDocuments(@NonNull String transactionId, @NonNull TransactionDocumentUploads transactionDocumentUploads) throws ApiException  {
        return (UploadsResponse)getClient().postWithAuth(UploadsResponse.class, String.format(getCustomListEndpointUrl("transaction_documents"), transactionId), transactionDocumentUploads);
    }
}

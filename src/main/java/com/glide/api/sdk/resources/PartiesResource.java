package com.glide.api.sdk.resources;

import com.glide.api.sdk.IGlideApiClient;
import com.glide.api.sdk.exceptions.ApiException;
import com.glide.api.sdk.models.requests.*;
import com.glide.api.sdk.models.responses.*;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class PartiesResource extends Resource implements IPartiesResource {

    public PartiesResource(IGlideApiClient client) {
        super(client, "parties", "/transactions/%s/parties");
    }

    public Party getDetail(@NonNull PartiesDetailRequest params) throws ApiException {
        return (Party)getClient().getWithAuth(Party.class, String.format(getDetailUrl(), params.getTransactionId(), params.getId()), params.getQueryParams());
    }

    public Party getDetail(@NonNull String transactionId, @NonNull String id) throws ApiException {
        return getDetail(new PartiesDetailRequest(transactionId, id));
    }

    public PartyList getMulti(@NonNull PartiesGetMultiRequest params) throws ApiException  {
        return (PartyList)getClient().getWithAuth(PartyList.class, String.format(getMultiUrl(), params.getTransactionId()), params.getQueryParams());
    }

    public PartyList getMulti(@NonNull String transactionId, @NonNull String[] ids) throws ApiException {
        return getMulti(new PartiesGetMultiRequest(transactionId, ids));
    }

    public PartyList list(@NonNull PartiesListRequest params) throws ApiException {
        return (PartyList)getClient().getWithAuth(PartyList.class, String.format(getListUrl(), params.getTransactionId()), params.getQueryParams());
    }

    public PartyList list(@NonNull String transactionId) throws ApiException {
        return list(new PartiesListRequest(transactionId));
    }
}

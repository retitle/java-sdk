package com.glide.api.sdk.resources;

import com.glide.api.sdk.exceptions.ApiException;
import com.glide.api.sdk.models.requests.*;
import com.glide.api.sdk.models.responses.*;

import lombok.NonNull;

public interface IPartiesResource {

    public Party getDetail(@NonNull PartiesDetailRequest params) throws ApiException;

    public Party getDetail(@NonNull String transactionId, @NonNull String id) throws ApiException;

    public PartyList getMulti(@NonNull PartiesGetMultiRequest params) throws ApiException;

    public PartyList getMulti(@NonNull String transactionId, @NonNull String[] ids) throws ApiException;

    public PartyList list(@NonNull PartiesListRequest params) throws ApiException;

    public PartyList list(@NonNull String transactionId) throws ApiException;
}

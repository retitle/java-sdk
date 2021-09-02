package com.glide.api.sdk.resources;

import com.glide.api.sdk.IGlideApiClient;
import com.glide.api.sdk.exceptions.ApiException;
import com.glide.api.sdk.models.requests.*;
import com.glide.api.sdk.models.responses.*;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class UsersResource extends Resource implements IUsersResource {

    public UsersResource(IGlideApiClient client) {
        super(client, "users", "/users");
    }

    public User current() throws ApiException  {
        return (User)getClient().getWithAuth(User.class, String.format(getCustomListEndpointUrl("current")));
    }
}

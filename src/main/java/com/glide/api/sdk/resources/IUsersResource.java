package com.glide.api.sdk.resources;

import com.glide.api.sdk.exceptions.ApiException;
import com.glide.api.sdk.models.requests.*;
import com.glide.api.sdk.models.responses.*;

import lombok.NonNull;

public interface IUsersResource {

    public User current() throws ApiException;
}

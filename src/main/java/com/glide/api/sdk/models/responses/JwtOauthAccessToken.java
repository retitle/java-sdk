package com.glide.api.sdk.models.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@NoArgsConstructor
public class JwtOauthAccessToken extends ResponseModel {
    private String accessToken;
    private int expiresIn;
    private String[] missingScopes;
    private String requestScopesUrl;
}

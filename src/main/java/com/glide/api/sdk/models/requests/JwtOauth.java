package com.glide.api.sdk.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class JwtOauth extends RequestModel {
    @NonNull String assertion;
    private String grantType = "JWT";

    public JwtOauth(@NonNull String assertion) {
        this(assertion, "JWT");
    }
}

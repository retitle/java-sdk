package com.glide.api.sdk.client;

import java.util.Calendar;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.glide.api.sdk.security.KeySet;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OAuthJwtGenerator {
    @NonNull private KeySet keySet;

    public String getToken(String iss, String sub, String aud, String[] scopes, int expiresIn) {
        Calendar iat = Calendar.getInstance();
        Calendar exp = (Calendar)iat.clone();
        exp.add(Calendar.SECOND, expiresIn);

        try {
            String token = JWT.create()
                .withIssuer(iss)
                .withSubject(sub)
                .withAudience(aud)
                .withArrayClaim("scopes", scopes != null ? scopes : new String[0])
                .withIssuedAt(iat.getTime())
                .withExpiresAt(exp.getTime())
                .sign(keySet.getAlgorithm());
            return token;
        } catch (JWTCreationException err) {
            // TODO re-throw as ApiException
            return "";
        }
    }
}

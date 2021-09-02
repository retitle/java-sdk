package com.glide.api.sdk.security;

import com.auth0.jwt.algorithms.Algorithm;

public interface KeySet {
    public Algorithm getAlgorithm();
}

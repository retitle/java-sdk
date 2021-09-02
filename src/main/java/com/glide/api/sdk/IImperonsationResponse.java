package com.glide.api.sdk;

public interface IImperonsationResponse {
    public boolean isOk();
    public void setOk(boolean ok);
    public String[] getMissingScopes();
    public void setMissingScopes(String[] missingScopes);
    public String getRequestScopesUrl();
    public void setRequestScopesUrl(String requestScopesUrl);
}

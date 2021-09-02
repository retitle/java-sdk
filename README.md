# Java Glide API SDK

> :warning: **The SDK and underlaying API are still being developed and current APIs are bound to change, not respecting backward compatibility conventions on versioning**

**Publication of the library is yet TBD, so currently it can be accessed by manually downloading the JAR using the links below:**
* [Jar](https://s3.us-west-1.amazonaws.com/devretitle/2da8b8fae2f14e2bacdfa36d/sdk-1.0.0-SNAPSHOT.jar)
* [Javadocs](https://s3.us-west-1.amazonaws.com/devretitle/bb69edb50065f788c8464867/sdk-1.0.0-SNAPSHOT-javadoc.jar)
* [Sources Jar](https://s3.us-west-1.amazonaws.com/devretitle/920353a6ae435f5866a70515/sdk-1.0.0-SNAPSHOT-sources.jar)

This SDK is meant to be used to more easily consume Glide's external APIs while using Java after an integration app has been set up for your Brokerage at Glide.

The underlying API's spec is available for both development and production environments on the links below:
* API root url: `https://api.dev.glide.com/` (development), & `https://api.glide.com/` (production)
* [Swagger docs](https://api.dev.glide.com/apidocs/)
* [Open API Spec](https://api.dev.glide.com/apispec_1.json)

## Integration App

If you don't have an integration app setup with Glide, please reach out to [Glide Engineering (engineering@glide.com)](mailto:engineering@glide.com) for guidance.
The integration app you use will dictate the client key and RS256 key pair values, and the associated brokerage's members are the Glide users you are allowed to impersonate while using these keys.

## Example usage

```java
package com.some.javapackage;

import java.util.ArrayList;
import java.util.List;

import com.glide.api.sdk.GlideApiClient;
import com.glide.api.sdk.exceptions.ApiException;
import com.glide.api.sdk.exceptions.ApiForbiddenException;
import com.glide.api.sdk.exceptions.ApiUnauthorizedException;
import com.glide.api.sdk.models.responses.ResponseList;
import com.glide.api.sdk.models.responses.Transaction;
import com.glide.api.sdk.models.responses.User;
import com.glide.api.sdk.security.Rsa256KeySet;

public class App {
    public static void main(String[] args) throws ApiException {
        String clientKey = "12345678-9abc-def0-1234-56789abcdef0";
        /*
            Also posible to get PEM formatted keys from memory using
            either `Rsa256KeySet.fromBytes` (recives byte[], byte[])
            or `Rsa256KeySet.fromStrings` (recives String, String)
        */
        Rsa256KeySet keySet = Rsa256KeySet.fromPaths(
            "/keys/public.pem",
            "/keys/private.pem"
        );

        // Can send `null` or empty `GlideApiClient.Options()` for production config,
        // or set Server to "api.glide.com"
        GlideApiClient.Options options = new GlideApiClient.Options();
        options.setServer("api.dev.glide.com");

        // GlideApiClient also has a constructor that does not receive options, to use default production
        // configuration, equivalent to sending empty options object
        GlideApiClient glideClient = new GlideApiClient(clientKey, keySet, options);

        // This will fail because no user is being impersonated
        try {
            glideClient.users.current();
        } catch (ApiUnauthorizedException err) {
            System.out.println("This error is expected: ");
            System.out.println(err);
        }

        String aGlideUsername = "your.user@domain.com";
        /*
            While impersonating a user, the SDK will seamlessly keep the access token refreshed.
            To stop impersonating you can use `glideClient.stopImpersonating()`, or you can use
            `glideClient.startImpersonating(...)` with different parameters to just change the current impersonated user/scopes.
            At any point in time
            * `glideClient.isImpersonating()`
            * `glideClient.impersonatingSub()`
            * `glideClient.impersonatingScopes()`
            can be used to find out whether or not an impersonation session is active, and find out details about it.
        */
        glideClient.startImpersonating(aGlideUsername);

        User user = glideClient.users.current();
        System.out.println(user);

        // This will fail because accessed resource (Transactions) requires missing TRANSACTIONS scope
        try {
            glideClient.transactions.list();
        } catch (ApiForbiddenException err) {
            System.out.println("This error is expected: ");
            System.out.println(err);
            System.out.println(err.getParams());
            System.out.println(glideClient.isImpersonating()); // A user is being impersonated
            System.out.println(glideClient.impersonatingSub()); // The user being impersonated
            System.out.println(glideClient.impersonatingScopes()); // No scopes included
        }

        List<String> scopes = new ArrayList<String>(){{
            add("TRANSACTIONS");
        }};
        glideClient.startImpersonating(aGlideUsername, scopes);

        ResponseList<Transaction> transactions = glideClient.transactions.list();
        for (Transaction t : transactions.getData()) {
            System.out.println(t);
        }

        // Make sure all these exist, else a 404 error will occur
        List<String> transactionIds = new ArrayList<String>() {{
            add("1965");
            add("1352");
            add("400");
        }};
        ResponseList<Transaction> multiTransactions = glideClient.transactions.getMulti(transactionIds);
        for (Transaction t : multiTransactions.getData()) {
            System.out.println(t);
        }

        String transactionId = transactionIds.get(0);
        Transaction transaction = glideClient.transactions.getDetail(transactionId);
        System.out.println(transaction);
    }
}
```

package com.glide.api.sdk.security;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import com.auth0.jwt.algorithms.Algorithm;
import com.glide.api.sdk.exceptions.ApiClientErrorException;

import lombok.NonNull;

public class Rsa256KeySet implements KeySet {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    private Rsa256KeySet(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    private static KeyFactory getKeyFactory() {
        try {
            return KeyFactory.getInstance("RSA");
        }  catch (NoSuchAlgorithmException e) {
            // Unreachable - no need to deal with
            return null;
        }
    }

    private static EncodedKeySpec getKeySpec(String rawPEMStr, Class<? extends EncodedKeySpec> specCls, String repalceStr) throws IOException, InvalidKeySpecException, SecurityException {
        String PEMStr = rawPEMStr
            .replace(String.format("-----BEGIN %s-----", repalceStr), "")
            .replaceAll(System.lineSeparator(), "")
            .replace(String.format("-----END %s-----", repalceStr), "");
        byte[] encodedPEM = Base64.getDecoder().decode(PEMStr);
        try {
            return specCls.getConstructor(encodedPEM.getClass()).newInstance(encodedPEM);
        } catch (InstantiationException|IllegalAccessException|InvocationTargetException|NoSuchMethodException err) {
            // Unreachable - no need to deal with
            return null;
        }
    }

    private static X509EncodedKeySpec getPublicKeySpec(String rawPEMStr)throws IOException, InvalidKeySpecException, SecurityException {
        return (X509EncodedKeySpec)getKeySpec(rawPEMStr, X509EncodedKeySpec.class, "PUBLIC KEY");
    }

    private static PKCS8EncodedKeySpec getPrivateKeySpec(String rawPEMStr)throws IOException, InvalidKeySpecException, SecurityException {
        return (PKCS8EncodedKeySpec)getKeySpec(rawPEMStr, PKCS8EncodedKeySpec.class, "PRIVATE KEY");
    }

    private static String getStringFromBytes(byte[] bytes) {
        return new String(bytes, Charset.defaultCharset());
    }

    private static X509EncodedKeySpec getPublicKeySpecFromBytes(byte[] rawPEMStrBytes) throws IOException, InvalidKeySpecException, SecurityException {
        return getPublicKeySpec(getStringFromBytes(rawPEMStrBytes));
    }

    private static PKCS8EncodedKeySpec getPrivateKeySpecFromBytes(byte[] rawPEMStrBytes) throws IOException, InvalidKeySpecException, SecurityException {
        return getPrivateKeySpec(getStringFromBytes(rawPEMStrBytes));
    }

    private static byte[] readBytesFrom(String strPath) throws IOException {
        Path path = Paths.get(strPath);
        return Files.readAllBytes(path);
    }

    private static X509EncodedKeySpec getPublicKeySpecFromPath(String keyStrPath) throws IOException, InvalidKeySpecException, SecurityException {
        return getPublicKeySpecFromBytes(readBytesFrom(keyStrPath));
    }

    private static PKCS8EncodedKeySpec getPrivateKeySpecFromPath(String keyStrPath) throws IOException, InvalidKeySpecException, SecurityException {
        return getPrivateKeySpecFromBytes(readBytesFrom(keyStrPath));
    }

    private static RSAPublicKey getPublicKeyFromSpec(X509EncodedKeySpec keySpec) throws IOException, InvalidKeySpecException, SecurityException {
        return (RSAPublicKey)getKeyFactory().generatePublic(keySpec);
    }

    private static RSAPrivateKey getPrivateKeyFromSpec(PKCS8EncodedKeySpec keySpec) throws IOException, InvalidKeySpecException, SecurityException {
        return (RSAPrivateKey)getKeyFactory().generatePrivate(keySpec);
    }

    private static void throwInvalidKeysApiException(Throwable err) throws ApiClientErrorException {
        throw new ApiClientErrorException("Invalid keys provided", err, -1, null, null);
    }

    public static Rsa256KeySet fromStrings(@NonNull String publicKeyStr, @NonNull String privateKeyStr) throws ApiClientErrorException {
        try {
            RSAPublicKey publicKey = getPublicKeyFromSpec(getPublicKeySpec(publicKeyStr));
            RSAPrivateKey privateKey = getPrivateKeyFromSpec(getPrivateKeySpec(privateKeyStr));
            return new Rsa256KeySet(publicKey, privateKey);
        } catch (IOException|InvalidKeySpecException|SecurityException err) {
            throwInvalidKeysApiException(err);
            return null;
        }
    }

    public static Rsa256KeySet fromBytes(@NonNull byte[] publicKeyBytes, @NonNull byte[] privateKeyBytes) throws ApiClientErrorException {
        try {
            RSAPublicKey publicKey = getPublicKeyFromSpec(getPublicKeySpecFromBytes(publicKeyBytes));
            RSAPrivateKey privateKey = getPrivateKeyFromSpec(getPrivateKeySpecFromBytes(privateKeyBytes));
            return new Rsa256KeySet(publicKey, privateKey);
        } catch (IOException|InvalidKeySpecException|SecurityException err) {
            throwInvalidKeysApiException(err);
            return null;
        }
    }

    public static Rsa256KeySet fromPaths(@NonNull String publicKeyPathStr, @NonNull String privateKeyPathStr) throws ApiClientErrorException {
        try {
            RSAPublicKey publicKey = getPublicKeyFromSpec(getPublicKeySpecFromPath(publicKeyPathStr));
            RSAPrivateKey privateKey = getPrivateKeyFromSpec(getPrivateKeySpecFromPath(privateKeyPathStr));
            return new Rsa256KeySet(publicKey, privateKey);
        } catch (IOException|InvalidKeySpecException|SecurityException err) {
            throwInvalidKeysApiException(err);
            return null;
        }
    }

    public Algorithm getAlgorithm() {
        return Algorithm.RSA256(publicKey, privateKey);
    }
}

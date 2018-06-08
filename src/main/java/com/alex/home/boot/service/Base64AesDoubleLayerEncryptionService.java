package com.alex.home.boot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * Encryption service for rest values.
 *
 * @author Oleksandr Ivanov
 */
@Service
@Slf4j
public class Base64AesDoubleLayerEncryptionService implements EncryptionService {
    private static byte[] initializationVector;

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private final String passphrase = "44ThisIsPassword";

    public String encrypt(String valueToEnc) throws GeneralSecurityException {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        AlgorithmParameters params = c.getParameters();

        // initialization vector and store it
        initializationVector = params.getParameterSpec(IvParameterSpec.class).getIV();

        byte[] encValue = c.doFinal(valueToEnc.getBytes(StandardCharsets.UTF_8));
        log.info("Total length encrypted {}", encValue.length);
        String encryptedValue = Base64.getUrlEncoder().withoutPadding().encodeToString(encValue);
        return encryptedValue + "==";
    }

    public String decrypt(String encryptedValue) throws GeneralSecurityException {
        if (encryptedValue == null) {
            return encryptedValue;
        }
        int markerIndex = encryptedValue.indexOf("==");
        if (markerIndex == -1) {
            return encryptedValue;
        }
        String substring = encryptedValue.substring(0, markerIndex);
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(initializationVector));
        byte[] decodedValue = Base64.getUrlDecoder().decode(substring.getBytes(StandardCharsets.UTF_8));
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    private Key generateKey() throws GeneralSecurityException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] salt = new byte[8];
        System.arraycopy(passphrase.getBytes(StandardCharsets.UTF_8), 0, salt, 0, 8);
        KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), salt, 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKey result = new SecretKeySpec(tmp.getEncoded(), "AES");
        return result;
    }
}

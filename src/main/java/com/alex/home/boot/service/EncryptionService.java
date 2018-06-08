package com.alex.home.boot.service;

/**
 * Encryption Service interface.
 *
 * @author Oleksandr Ivanov
 */
public interface EncryptionService {

    String encrypt(String valueToEnc) throws Exception;

    String decrypt(String valueToEnc) throws Exception;
}

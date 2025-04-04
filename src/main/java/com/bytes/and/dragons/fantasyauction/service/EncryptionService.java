package com.bytes.and.dragons.fantasyauction.service;

import com.bytes.and.dragons.fantasyauction.config.EncryptionConstants;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    private final TextEncryptor textEncryptor;

    public EncryptionService() {
        this.textEncryptor = Encryptors.text(EncryptionConstants.SECRET, EncryptionConstants.SALT);
    }

    public String encrypt(String text) {
        return textEncryptor.encrypt(text);
    }

    public String decrypt(String encryptedText) {
        return textEncryptor.decrypt(encryptedText);
    }
}

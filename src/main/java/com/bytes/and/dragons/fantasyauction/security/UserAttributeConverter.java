package com.bytes.and.dragons.fantasyauction.security;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@Converter
public class UserAttributeConverter implements AttributeConverter<String, String> {

    @Value("${app.encryption.password}")
    private String encryptionPassword;

    @Value("${app.encryption.salt}")
    private String encryptionSalt;

    private TextEncryptor encryptor;

    @PostConstruct
    public void init() {
        encryptor = Encryptors.delux(encryptionPassword, encryptionSalt);
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return attribute == null ? null : encryptor.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData == null ? null : encryptor.decrypt(dbData);
    }
}

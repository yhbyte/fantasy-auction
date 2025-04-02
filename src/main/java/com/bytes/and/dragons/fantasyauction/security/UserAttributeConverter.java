package com.bytes.and.dragons.fantasyauction.security;

import com.bytes.and.dragons.fantasyauction.config.EncryptionConstants;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@Converter
public class UserAttributeConverter implements AttributeConverter<String, String> {

    private final TextEncryptor encryptor;

    public UserAttributeConverter() {
        this.encryptor = Encryptors.text(EncryptionConstants.SECRET, EncryptionConstants.SALT);
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

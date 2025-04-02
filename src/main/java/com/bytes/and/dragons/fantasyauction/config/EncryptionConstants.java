package com.bytes.and.dragons.fantasyauction.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EncryptionConstants {

    // TODO add env config values
    public static final String SECRET = "VeryStrongSecretKeyForEncryptionThatIsAtLeast32Chars!";
    public static final String SALT = "0123456789abcdef";

}

package com.example;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JasyptConfigTest {
    @Test
    void jasypt(){
        String plainText = "painText";
        String cipherText = jasyptEncrypt(plainText);

        System.out.println("cipherText : " + cipherText);

        Assertions.assertThat(plainText).isEqualTo(jasyptDecryt(cipherText));
    }

    private String jasyptEncrypt(String input) {
        String key = "secret";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
        return encryptor.encrypt(input);
    }

    private String jasyptDecryt(String input){
        String key = "secret";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
        return encryptor.decrypt(input);
    }
}
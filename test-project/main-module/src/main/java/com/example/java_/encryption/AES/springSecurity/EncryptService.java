package com.example.java_.encryption.AES.springSecurity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class EncryptService {
	enum Encrypt {
		CRYPT, SALT
	}

	@Value("${spring.security.crypto.encrypt.key}")
	private String secretKey;

	public Map<Encrypt, String> encrypt(String plaintext) {
		String salt = generateRandomSalt(16);
		return encrypt(plaintext, salt);
	}

	public Map<Encrypt, String> encrypt(String plaintext, String salt) {
		TextEncryptor encryptor = Encryptors.text(secretKey, salt);
		return new HashMap<>(){{
			put(Encrypt.CRYPT, encryptor.encrypt(plaintext));
			put(Encrypt.SALT, salt);
		}};
	}

	public String decrypt(String encryptedText, String salt) {
		TextEncryptor encryptor = Encryptors.text(secretKey, salt);
		return encryptor.decrypt(encryptedText);
	}

	public String generateRandomSalt(int length) {
		char[] chars = "abcdef1234567890".toCharArray();
		StringBuilder salt = new StringBuilder();

		SecureRandom random = new SecureRandom();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(chars.length);
			salt.append(chars[index]);
		}

		return salt.toString();
	}
}
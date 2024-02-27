package com.example.java_.encryption.AES.springSecurity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import java.security.SecureRandom;

public class EncryptService {
	@Value("${spring.security.crypto.encrypt.key}")
	private String secretKey;

	public String[] encrypt(String plaintext) {
		String salt = generateRandomSalt(16);
		TextEncryptor encryptor = Encryptors.text(secretKey, salt);
		return new String[]{encryptor.encrypt(plaintext), salt};
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

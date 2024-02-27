package com.example.java_.encryption.AES.springSecurity;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static com.example.java_.encryption.AES.springSecurity.EncryptService.Encrypt.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EncryptServiceTest {
	@Test
	public void testEncryptDecrypt() {
		String secretKey = "asdf1234";

		EncryptService encryptService = new EncryptService();

		ReflectionTestUtils.setField(encryptService, "secretKey", secretKey);

		// 테스트할 평문
		String plaintext = "Hello, World!";

		// 암호화
		Map<EncryptService.Encrypt, String> encrtpy = encryptService.encrypt(plaintext);
		String encryptedText = encrtpy.get(CRYPT);
		String salt = encrtpy.get(SALT);

		// 복호화
		String decryptedText = encryptService.decrypt(encryptedText, salt);

		// 원본과 복호화된 값이 같은지 확인
		assertEquals(plaintext, decryptedText);
	}
}
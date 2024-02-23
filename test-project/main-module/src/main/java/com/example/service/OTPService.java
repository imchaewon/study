package com.example.service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OTPService {
	private final GoogleAuthenticator gAuth;

	public String generateKey(String userId) {
		final String issuer = "vista";
		GoogleAuthenticatorKey key = gAuth.createCredentials();
		String url = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL(issuer, userId, key);
		log.info("encode key: {}", key.getKey());
		log.info("url: {} ", url);
		return url;
	}

	public boolean auth(String userCode, String key) {
		return gAuth.authorize(userCode, Integer.parseInt(key));
	}

}
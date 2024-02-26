package com.example.controller;

import com.example.service.OTPService;
import com.example.service.QrService;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("two-factor-auth")
@RequiredArgsConstructor
public class TwoFactorAuthController {
	private final OTPService otpService;
	private final QrService qrService;

	@GetMapping("otp-register")
	public ResponseEntity<byte[]> register() throws WriterException {
		String userId = "user123"; // Fixme: 동적 값 할당
		String url = otpService.generateKey(userId);
		return qrService.generate(url);
	}

	@GetMapping("otp-auth")
	public boolean authenticate(@RequestParam("secretKey") String secretKey, @RequestParam("pwd") String pwd){
		return otpService.auth(secretKey, pwd);
	}
}









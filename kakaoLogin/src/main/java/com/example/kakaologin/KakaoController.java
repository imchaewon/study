package com.example.kakaologin;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class KakaoController{

	@Autowired
	KakaoService ks;

	@Value("${kakao.restApiKey}")
	String restApiKey;

	@Value("${kakao.loginUri}")
	String loginUri;

	@Value("${kakao.logoutUri}")
	String logoutUri;

	@GetMapping("/do")
	public String loginPage(HttpServletRequest req)
	{
		req.setAttribute("REST_API_KEY", restApiKey);
		req.setAttribute("LOGIN_REDIRECT_URI", loginUri);
		return "kakaoCI/login";
	}

	@GetMapping("/kakao")
	public String getCI(@RequestParam String code, Model model) throws IOException {
		System.out.println("code1: "+code);
		String access_token = ks.getToken(restApiKey, code);
		Map<String, Object> userInfo = ks.getUserInfo(access_token);
		String agreementInfo = ks.getAgreementInfo(access_token);
		model.addAttribute("code", code);
		model.addAttribute("access_token", access_token);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("agreementInfo", agreementInfo);
		model.addAttribute("REST_API_KEY", restApiKey);
		model.addAttribute("LOGOUT_REDIRECT_URI", logoutUri);

		//ci는 비즈니스 전환후 검수신청 -> 허락받아야 수집 가능
		return "index";
	}

}
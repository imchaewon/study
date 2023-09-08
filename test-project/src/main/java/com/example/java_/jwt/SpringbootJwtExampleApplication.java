package com.example.java_.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringbootJwtExampleApplication implements CommandLineRunner {

	@Autowired
	private JwtService jwtService;

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootJwtExampleApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJwtExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		User u = new User();
		u.setUserId("sujin");
		u.setName("kimdongyuel");
		u.setAuthority(Arrays.asList("USER"));


		LOGGER.debug("creating jwt...");

		String token = jwtService.createLoginToken(u);
		LOGGER.debug("jwt 생성 :"  +  token);
		// jwt 생성 :eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTQ5MzMwMzcsImlhdCI6MTYxNDkyOTQzNywidXNlciI6eyJ1c2VySWQiOiJzdWppbiIsInBhc3N3b3JkIjpudWxsLCJhdXRob3JpdHkiOlsiVVNFUiJdLCJlbmFibGVkIjpmYWxzZSwibmFtZSI6IuydtOyImOynhCJ9fQ.FsP6XGQ2tLJ9kO8NZMOP3OtZu69YK1vxWhNK4XGyEmU
//		

//		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTQ5MzMwMzcsImlhdCI6MTYxNDkyOTQzNywidXNlciI6eyJ1c2VySWQiOiJzdWppbiIsInBhc3N3b3JkIjpudWxsLCJhdXRob3JpdHkiOlsiVVNFUiJdLCJlbmFibGVkIjpmYWxzZSwibmFtZSI6IuydtOyImOynhCJ9fQ.FsP6XGQ2tLJ9kO8NZMOP3OtZu69YK1vxWhNK4XGyEmU";

		LOGGER.debug("jwt decoding... ");
		User user = jwtService.getUser(token);
		LOGGER.debug("디코드된 jwt : " +  user);


//		- JWT를 생성할 때와 복호화할 때의 비밀키를 다르게 설정: SignatureException 발생
//		- 위조한 JWT에 대해 복호화를 시도:  MalformedJwtException 발생
//		- 만료기간이 지난 JWT에 대해 복호화를 시도:  ExpiredJwtException 발생

	}

}
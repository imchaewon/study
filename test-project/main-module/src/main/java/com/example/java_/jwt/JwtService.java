package com.example.java_.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class JwtService{

	// 비밀키(JWT HMAC-SHA 알고리즘에서는 키의 크기가 해시 출력 크기보다 커야 함)
	private static final String SECRET_KEY =  "secret12387912355123789235839287654321";

	// 로그
	private static final Logger LOGGER  = LoggerFactory.getLogger(JwtService.class);

	// key
	private static final String DATA_KEY = "user";


	//매퍼
	@Autowired
	private ObjectMapper objectMapper;


	//[0] User 정보를 이용해서 - JWT 생성
	public String createLoginToken(User user) {

		//현재시간
		long curTime = System.currentTimeMillis();

		//[1] Jwts 라이브러리로부터 JWT 생성 - builder 패턴
		return  Jwts.builder()
				.setSubject("Test JWT")

				//[2]
				//setHeaderParam 메소드를 통해 JWT 헤더가 지닐 정보들을 담는다.
				//alg 의 경우는 default 값이 SHA256이므로 따로 설정할 필요는 없다.
				//typ 를 셋팅 안해주면 오류 발생한다.
				.setHeaderParam("typ", "JWT")

				//[3] 만료 시간
				.setExpiration(new Date(curTime + 3600000))

				//[4] 발급 시간
				.setIssuedAt(new Date(curTime))


				//[5] Payload 에 Private Claims 를 담기 위해 claim 메소드를 이용한다.
				// private claim으로 VO객체를 추가할 수 있음
				.claim(DATA_KEY, user)


				//[6] 복호화 할때 사용하는 시그니처 설정.
				// header의 인코딩값 + payload의 인코딩값 + 비밀키 = 시그니처
				// signWith api는 해싱알고리즘과 비밀키가 필요하다.
				.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))

				//생성
				.compact();

		//"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTQ5MzMwMzcsImlhdCI6MTYxNDkyOTQzNywidXNlciI6eyJ1c2VySWQiOiJzdWppbiIsInBhc3N3b3JkIjpudWxsLCJhdXRob3JpdHkiOlsiVVNFUiJdLCJlbmFibGVkIjpmYWxzZSwibmFtZSI6IuydtOyImOynhCJ9fQ.FsP6XGQ2tLJ9kO8NZMOP3OtZu69YK1vxWhNK4XGyEmU";
	}

	//JWT 복호화
	public User getUser(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parserBuilder()
					.setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
					.build()
					.parseClaimsJws(jwt);

			Map<String, Object> body = claims.getBody();
			return objectMapper.convertValue(body.get(DATA_KEY), User.class);
		} catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
			throw new JWTException("decoding failed");
		}
	}
}

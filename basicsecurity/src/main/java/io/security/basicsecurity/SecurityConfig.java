package io.security.basicsecurity;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(req -> req
						.requestMatchers("/specific-path/*", "/js/**") // /specific-path/* 및 /js/**로 시작하는 요청은 모든 사용자에게 허용
						.permitAll()
						.requestMatchers("/member/")
						.hasRole("Member")
						.anyRequest().authenticated()) // 이외의 모든 요청은 인증된 사용자에게만 허용
				.formLogin(formLogin -> formLogin
//						.loginPage("/loginPage") // 로그인페이지로 쓸 URL
//						.defaultSuccessUrl("/successcuccess") // 성공 시 이동될 url. successHandler와 동시에 사용하지 않음
//						.failureUrl("/failfail") // 실패 시 이동될 url. failureHandler와 동시에 사용하지 않음
						.usernameParameter("userId") // 아이디 input태그의 name태그 값 설정
						.passwordParameter("passwd") // 비밀번호 input태그의 name태그 값 설정
						.loginProcessingUrl("/login_proc") // 로그인 후 이동할 URL. 성공하든 실패하든 이동됨
						.successHandler((request, response, authentication) -> { // 성공 시 실행할 메서드
							System.out.println("authentication = " + authentication.getName());
						})
						.failureHandler((request, response, exception) -> { // 실패 시 실행할 메서드
							System.out.println("exception = " + exception.getMessage());
							response.sendRedirect("/login");
						})
//						.permitAll() // loginPage 메서드에 지정한 URL을 허용.
				)
				.httpBasic(withDefaults()) // 사용자 이름과 비밀번호를 요청 헤더에 인코딩하여 전송하고, 서버는 이를 검증하여 사용자를 인증하는 방식.
					// withDefaults()는 HttpSecurity 빌더에 대해 기본적인 HTTP Basic 인증 설정을 적용하는 메서드.
					// 이를 호출하면 기본적으로 사용자가 로그인할 때 브라우저가 자동으로 사용자 이름과 비밀번호를 물어보는 HTTP Basic 인증 대화상자가 나타남
					// formLogin 과 동시에 사용하지 않음
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("login")
						.addLogoutHandler((request, response, authentication) -> {
							HttpSession session = request.getSession();
							session.invalidate();
						})
						.logoutSuccessHandler((request, response, authentication) -> response.sendRedirect("login"))
						.deleteCookies("remember-me")
				)
				.rememberMe(e -> {
					e.rememberMeCookieName("remember");
					e.rememberMeParameter("remember");
					e.tokenValiditySeconds(3600);
//					e.alwaysRemember(true);
					e.userDetailsService(userDetailsService);
				})
				.sessionManagement(e -> {
					e.maximumSessions(1)
							.maxSessionsPreventsLogin(true);
				});
		return http.build();
	}
}
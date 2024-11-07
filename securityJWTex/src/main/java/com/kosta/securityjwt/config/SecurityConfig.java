package com.kosta.securityjwt.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import com.kosta.securityjwt.jwt.JwtAuthenticationFilter;

//@Configuration // IoC빈 등록
//@EnableWebSecurity //필터체인 관리 시작 어노테이션
//@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true) //controller 전체가 아닌 (/admin/**)url마다 각각 따로 적용하고 싶을 때 사용
//public class SecurityConfig {
//	
//	@Autowired
//	private CorsFilter corsFilter;
//	
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
//		return authenticationConfiguration.getAuthenticationManager();
//	}
//	
//	// password 암호화 객체만 생성해주면 알아서 암호화 해줌
//	// db에 암호화 시킨 번호를 저장해야 security가 login처리 할 수 있다.
//	@Bean
//	public BCryptPasswordEncoder encoderPassword() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);	
//		
//		http.addFilter(corsFilter) // 다른 도메인 접근 허용
//			.csrf().disable() // csrf공격 비활성화
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 일반 session 비활성화 (securitysession이 아니)
//			
//			// 로그인
//			http.formLogin().disable() //로그인 폼 비활성화 (프론트로 화면 구현)
//			.httpBasic().disable() //httpBasic은 header에 username,password를 암호화 하지 않은 상태로 주고 받기 때문에 이를 사용 하지 않음
//			.addFilterAt(new JwtAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);
//			//  UsernamePasswordAuthenticationFilter 기본적으로 사용자 이름과 비밀번호를 통한 사용자 인증처리 필터보다  
//			//JwtAuthenticationFilter(헤더의 JWT토큰을 검사하여 JWT인증 ) 를 우선으로 수행 하여 JWT 토큰 사용 인증을 우선시 함  
//		return http.build();
//		
//	}
//}

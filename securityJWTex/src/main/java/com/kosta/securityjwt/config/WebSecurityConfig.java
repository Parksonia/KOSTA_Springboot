package com.kosta.securityjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import com.kosta.securityjwt.jwt.JwtAuthenticationFilter;
import com.kosta.securityjwt.jwt.JwtAuthorizationFilter;
import com.kosta.securityjwt.repository.UserRepository;

@Configuration // IoC빈 등록
@EnableWebSecurity //필터체인 관리 시작 어노테이션
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true) 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CorsFilter corsFilter;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Bean
	public BCryptPasswordEncoder encorderPassword() {
		return new BCryptPasswordEncoder();
	}
	@Override
	public void configure (HttpSecurity http) throws Exception {
//		AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);	
		
		http.addFilter(corsFilter) // 다른 도메인 접근 허용
			.csrf().disable() // csrf공격 비활성화
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // session 비활성화

		//로그인
		http.formLogin().disable() // 로그인 폼 비활성화
		.httpBasic().disable()
		.addFilterAt(new JwtAuthenticationFilter(authenticationManager()),UsernamePasswordAuthenticationFilter.class);
	
		
		http.addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepo))
			.authorizeRequests()
			.antMatchers("/user/**").authenticated() // 로그인 필수 
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
			.antMatchers("/manager/**").access("hasRole('ROLE_MANAGER')")
			.anyRequest().permitAll();
		
	}
	
}

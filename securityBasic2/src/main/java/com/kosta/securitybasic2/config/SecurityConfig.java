package com.kosta.securitybasic2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // IoC빈 등록
@EnableWebSecurity //필터체인 관리 시작 어노테이션
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true) //controller 전체가 아닌 (/admin/**)url마다 각각 따로 적용하고 싶을 때 사용
public class SecurityConfig {

	//이전에 상속받아  사용 하던 형태, 
	// 필터 체인으로 security로 가기전에 가로채게 됨
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable(); // cross site request forgery attack : 크로스 사이트 요청 위조 공격 을 방어하겠다는 의미 
		http.authorizeRequests()// authorize :권한과 관련
		 	//.antMatchers("/join").permitAll()
			.antMatchers("/user/**").authenticated()  // /user url로 들어오면 로그인 필수 
//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")  // 로그인 &권한이 ADMIN이거나MNAGER만 허용 
//			.antMatchers("/manager/**").access("hasRole('ROLE_MANAGER')") // 로그인 && 권한이 MANGER만 허용 
//			.antMatchers("/manager/**").hasRole("ROLE_MANAGER") // 권한 하나만 설정할 때 .access대신 .hasRole 사용도 가능
			.anyRequest().permitAll()
			.and()
			.formLogin() //나의 로그인 폼 사용
			.loginPage("/login") // 내가 설정 한 로그인 url로 접속하면 화면은 내가 설정한 화면으로가지만 로그인 처리는 
			.loginProcessingUrl("/loginProc") // spring security가 로그인 프로세스 처리를 가로채게 됨 따라서 내가 /loginProc을 구현하는게 아님 
											//이 로그인 과정에서 필요한 것은 PrincipalDetails를 만들어 주는 것이다. 
			.defaultSuccessUrl("/"); //로그인 성공 시 넘어가는 페이지
			
		return http.build();
		
	}
	// password 암호화 객체만 생성해주면 알아서 암호화 해줌
	// db에 암호화 시킨 번호를 저장해야 security가 login처리 할 수 있다.
	@Bean
	public BCryptPasswordEncoder encoderPassword() {
		return new BCryptPasswordEncoder();
	}
	
}
// authorize :권한과 관련
//// authenticate : 인가,로그인 

//@Configuration 
//: 설정에 관련된 클래스에 붙여줌, IoC(역제어:내가 설정한걸 프레임워크에 요청,bean등록) 

//@EnableWebSecurity
//: 필터 체인 관리 시작 어노테이션
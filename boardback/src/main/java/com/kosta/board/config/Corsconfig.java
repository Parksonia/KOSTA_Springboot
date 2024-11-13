package com.kosta.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.kosta.board.jwt.JwtProperties;

@Configuration
public class Corsconfig {

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		//config.addAllowedOrigin("http://localhost:3000"); // 접근 가능 포트  
		config.addAllowedOriginPattern("*"); //모든 접근 허용
		config.addAllowedMethod("*"); //"GET","POST","PUT","DELETE"
		config.addAllowedHeader("*"); // Access-Control-Allow-Headers 
		config.addExposedHeader(JwtProperties.HEADER_STRING); //클라이언트(리액트 등 )가 응답에 접근할 수 있는 헤더 추가 
		source.registerCorsConfiguration("/*", config);
		source.registerCorsConfiguration("/*/*", config);
		
		
		
		return new CorsFilter(source);
	}
}

//@Configuration 
//: 설정에 관련된 클래스에 붙여줌, IoC(역제어:내가 설정한걸 프레임워크에 요청,bean등록) 

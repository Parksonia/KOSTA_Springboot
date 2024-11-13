package com.kosta.board.oauth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosta.board.auth.PrincipalDetails;
import com.kosta.board.jwt.JwtProperties;
import com.kosta.board.jwt.JwtToken;

@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

	private static final String URI = "http://localhost:3000/login";
	private JwtToken jwtToken = new JwtToken();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
			
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal(); // user 정보를 다 가지고 있음
		//토큰 만들기 
		String accessToken  = jwtToken.makeAccessToken(principalDetails.getUsername());
		String refreshToken = jwtToken.makeRefreshToken(principalDetails.getUsername());
	
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,String> map = new HashMap<>();
		map.put("access_token",JwtProperties.TOKEN_PREFIX+accessToken); 
		map.put("refresh_token", JwtProperties.TOKEN_PREFIX+refreshToken);
		
		String token = objectMapper.writeValueAsString(map); // map을 jsonString으로 변환하여 내보냄
		System.out.println(token);
		
		String redirectUrl = UriComponentsBuilder.fromUriString(URI)
				.queryParam("token", token)
				.build().toUriString();
		
		// 성공 시 , 토큰을 만들고 
		// 정한 uri로 리다이렉트 시킴 (프론트 리액트 uri)
		response.sendRedirect(redirectUrl);
		
	}

}

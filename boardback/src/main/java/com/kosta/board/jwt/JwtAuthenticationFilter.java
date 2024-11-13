package com.kosta.board.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosta.board.auth.PrincipalDetails;


// 이전 방식은 session방식으로 security session에 들어가는 타입은 Authentication 타입의 객체여야 한다.
//Authentication 객체안에 User정보를 넣었다 => UserDetailsService(new Authentication(new User Details(new user) )

//Springsecurity 필터 체인으로 security로 가기전에 가로채게 됨
//토큰으로 인가처리
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private JwtToken jwtToken = new JwtToken();
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	//super의 attempAuthentication메소드가 실행되고 성공 시 , successfuleAuthentication이 호출됨.
	//attempAuthentication 는 username과 password를 체크해서 내보내줌 
	//attempAuthentication 메서드가 리턴해준 Authentication 을 파라미터로 받아옴 (Authentication에 사용자 인증 정보가 들어있음)
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		PrincipalDetails principalDetails = (PrincipalDetails)authResult.getPrincipal(); // user 정보를 다 가지고 있음
	
		
		// 이제  이를 가지고 토큰을 생성
		String accessToken  = jwtToken.makeAccessToken(principalDetails.getUsername());
		String refreshToken = jwtToken.makeRefreshToken(principalDetails.getUsername());
	
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,String> map = new HashMap<>();
		map.put("access_token",JwtProperties.TOKEN_PREFIX+accessToken); 
		map.put("refresh_token", JwtProperties.TOKEN_PREFIX+refreshToken);
		
		String token = objectMapper.writeValueAsString(map); // map을 jsonString으로 변환하여 내보냄
		System.out.println(token);
		
		response.addHeader(JwtProperties.HEADER_STRING, token);  // 헤더에 넣어줌
		response.setContentType("application/json; charset=UTF-8");
	
	}
	
// 자식이 오버라이딩 하지 않아도 부모꺼가 알아서 실행되기 때문에 오버라이딩 하지 않음 	
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//			throws AuthenticationException {
//		// TODO Auto-generated method stub
//		return super.attemptAuthentication(request, response);
//	}
}

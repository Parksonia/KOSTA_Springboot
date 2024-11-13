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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosta.board.auth.PrincipalDetails;
import com.kosta.board.entity.Member;
import com.kosta.board.repository.MemberRespository;

// 인가 : 로그인 처리가 되어야만 하는 처리가 들어왔을 때 실행됨
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	@Autowired
	private MemberRespository userRepo;
	
	@Autowired
	private JwtToken jwtToken = new JwtToken();
	

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
			MemberRespository userRepo) {
		
		super(authenticationManager);
		this.userRepo = userRepo;
	
	}

		 @Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			String uri = request.getRequestURI();
			System.out.println(uri);
			
			//로그인(인증)이 필요없는 요청은 그대로 진행
			if(!(uri.contains("/user")|| uri.contains("/admin")||uri.contains("/manager"))) {
				chain.doFilter(request, response);
				return;
			}
			
			String authentication = request.getHeader(JwtProperties.HEADER_STRING); //"Authorization"
			if(authentication ==null ) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"로그인 필요");
				return;
			}
			

			ObjectMapper objectMapper = new ObjectMapper();
			Map<String,String> token = objectMapper.readValue(authentication, Map.class);
			System.out.println(token);
			
			//accessToken :header로부터 acccessToken 을 가져와서  bear 체크 (validate check)
			String accessToken = token.get("access_token");
			if(!accessToken.startsWith(JwtProperties.TOKEN_PREFIX)) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"로그인 필요");
				return;
			}
			accessToken = accessToken.replace(JwtProperties.TOKEN_PREFIX, "");
			try {
				//1. Access Token
				//1-1. 보안키, 만료시간 체크
				String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET))
						.build()
						.verify(accessToken)
						.getClaim("sub")
						.asString();
				System.out.println(username);
				
				//1-2. username 체크
				if(username == null || username.equals("")) throw new Exception("로그인 필요"); // 사용자가 없을때 
				
				// db에 없어도 예외처리
				Member user = userRepo.findByUsername(username);
				if(user == null ) throw new Exception("로그인 필요"); // 사용자가DB에 없을때 
				
			
				//1-3. User를 Authentication 객체로 생성하여 SecuritySession에 넣어줌 
				// springSecuritySession은(==SecurityContextHolder) 토큰을 모르기 때문에 컨트롤러에서 사용자 정보를 알 수 있도록 // 사용자 정보 토큰을 수동으로 생성해서  넣어준다. 
				PrincipalDetails principalDetails = new PrincipalDetails(user);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principalDetails,null,
																								principalDetails.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(auth); // Authentication 객체에 User정보를 감싸서 SecuritySession에 전달 
				chain.doFilter(request, response); // 컨트롤러로 보냄
				return; 
				
			} catch (Exception e) {
				e.printStackTrace();
				
				try {
				//2. Refresh Token check : Access Token invalidate일 경우 
				String refreshToken = token.get("refresh_token");
				if(!refreshToken.startsWith(JwtProperties.TOKEN_PREFIX)) {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"로그인 필요");
					return;
				}
				refreshToken  = refreshToken.replace(JwtProperties.TOKEN_PREFIX, "");
				
				//2-1. 보안키, 만료시간 체크
				String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET))
						.build()
						.verify(refreshToken)
						.getClaim("sub")
						.asString();
				System.out.println(username);
				
				//2-2. username 체크
				if(username == null || username.equals("")) throw new Exception("로그인 필요"); // 사용자가 없을때 
				
				Member user = userRepo.findByUsername(username);
				if(user == null ) throw new Exception("로그인 필요"); // 사용자가DB에 없을때 
				
				//accessToekn, refreshToekn 다시 만들어 보낸다. 
				String reAccessToken = jwtToken.makeAccessToken(username);
				String reRefreshToken = jwtToken.makeRefreshToken(username);
				
				Map<String,String> map = new HashMap<>();
				
				map.put("access_token", JwtProperties.TOKEN_PREFIX+reAccessToken);
				map.put("refresh_token", JwtProperties.TOKEN_PREFIX+reRefreshToken);
				String reToken = objectMapper.writeValueAsString(map); //map->jsonString
				response.addHeader(JwtProperties.HEADER_STRING, reToken);
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().print("token");
				
				}catch(Exception e2) {
				// 토큰이(access,refresh) 둘다 타당하지 않다는 의미...
					e2.printStackTrace();
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"로그인 필요");
				
				}
			
			}
			
		}

	
	
}

package com.kosta.securityjwt.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.kosta.securityjwt.auth.PrincipalDetails;
import com.kosta.securityjwt.entity.User;
import com.kosta.securityjwt.repository.UserRepository;

@Service
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private UserRepository userRepo;
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println(userRequest.getClientRegistration());
		System.out.println(userRequest.getAccessToken());
		System.out.println(userRequest.getAdditionalParameters());
		
		OAuth2User oAuth2User = super.loadUser(userRequest); // 최종 사용자 정보 얻어옴 
		System.out.println(oAuth2User);
		System.out.println(oAuth2User.getAttributes());
		
		return processOAuth2User(userRequest,oAuth2User);
	}
	
	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
		OAuth2UserInfo oAuth2UserInfo = null;
		//카카오
		if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
			System.out.println("카카오 로그인");
			oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
		//네이버
		} else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttribute("response"));
		
		//지원x 소셜 로그인들	
		}else {
			System.out.println("카카오와 네이버만 지원합니다.");
		}
		//1.DB에서 조회 
		User user = userRepo.findByProviderAndProviderId(oAuth2UserInfo.getProvider(),oAuth2UserInfo.getProviderId());
		
		//1-1.이미 가입되어 있으면 정보 수정
		if(user!=null) {
			user.setEmail(oAuth2UserInfo.getEmail());
			userRepo.save(user);
			
		//1-2. 가입되어 있지 않으면 삽입
		}else {
			User nUser = User.builder()
					.username(oAuth2UserInfo.getProviderId())
					.email(oAuth2UserInfo.getEmail())
					.roles("ROLE_USER")
					.provider(oAuth2UserInfo.getProvider())
					.providerId(oAuth2UserInfo.getProviderId())
					.build();
			userRepo.save(nUser);
		}
		

		return new PrincipalDetails(user,oAuth2User.getAttributes());
		
	}
	
}

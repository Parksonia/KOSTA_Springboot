package com.kosta.securityjwt.oauth;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo {

	private Map<String,Object> attributes;
	
	public KakaoUserInfo(Map<String,Object> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public String getProviderId() {
		
		return String.valueOf(attributes.get("id"));
	}

	@Override
	public String getProvider() {
		
		return "Kakao";
	}

	@Override
	public String getEmail() {
		//카카오의 email형태가 객체이기 때문에 벗겨내야함?
		Map<String,Object> kakao_account= (Map<String,Object>)attributes.get("kakao_account");
		
		return (String)kakao_account.get("email");
	}

	@Override
	public String getName() {
		
		return (String)attributes.get("profile_nickname");
	}

}

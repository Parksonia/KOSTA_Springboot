package com.kosta.board.oauth;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo {

	private Map<String,Object> attributes;
	
	public NaverUserInfo(Map<String,Object> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public String getProviderId() {
		
		return  String.valueOf(attributes.get("id"));
	}

	@Override
	public String getProvider() {
		
		return "Naver";
	}

	@Override
	public String getEmail() {
		
		return (String)attributes.get("email");
	}

	@Override
	public String getName() {
		
		return (String)attributes.get("name");
	}

}
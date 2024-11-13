package com.kosta.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.board.entity.Member;

public interface MemberRespository extends JpaRepository<Member, String> {

	public Member findByUsername(String username);
	
	//소셜로그인 
	public Member findByProviderAndProviderId(String provider,String providerId);
	
}

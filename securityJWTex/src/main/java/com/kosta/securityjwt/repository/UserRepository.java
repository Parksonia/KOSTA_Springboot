package com.kosta.securityjwt.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.securityjwt.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByUsername(String username);
	
	//소셜로그인 
	public User findByProviderAndProviderId(String provider,String providerId);
	
}

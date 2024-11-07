package com.kosta.securityjwt.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kosta.securityjwt.entity.User;
import com.kosta.securityjwt.repository.UserRepository;


//loginProc요청이 오면 자동으로 UserDetailService의 타입으로 IoC 되어 있는 loadUserByUsername 함수가 호출 
@Service
public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user != null) return new PrincipalDetails(user);
		System.out.println(user);
		return null;
	}

}

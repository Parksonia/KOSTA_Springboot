package com.kosta.securitybasic2.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kosta.securitybasic2.entity.User;
import com.kosta.securitybasic2.repository.UserRepository;

// UserDatilsService를 상속받아야만 그 위에 있는 
//Security 설정에서 loginProcessUrl("/loginProc)
//loginProc 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어 있는 loadUserByname함수가 호출된다.

@Service
public class PrincipalDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("no user"));
		
		// UserDatils 서비스로 리턴을 해야함 
		return new PrincipalDetails(user);
	}

}

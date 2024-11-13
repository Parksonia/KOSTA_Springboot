package com.kosta.board.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kosta.board.entity.Member;
import com.kosta.board.repository.MemberRespository;


//loginProc요청이 오면 자동으로 UserDetailService의 타입으로 IoC 되어 있는 loadUserByUsername 함수가 호출 
@Service
public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	private MemberRespository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member user = userRepo.findByUsername(username);
		if(user != null) return new PrincipalDetails(user);
		System.out.println(user);
		return null;
	}

}

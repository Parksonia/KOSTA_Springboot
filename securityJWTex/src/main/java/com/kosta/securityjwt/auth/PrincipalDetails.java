package com.kosta.securityjwt.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.kosta.securityjwt.entity.User;

import lombok.Data;

// Security가 ./loginProc 주소를 낚아채 로그인을 진행시켜준다.
// 로그인 진행이 완료되면 security session을 만들어 준다(Security ContextHolder)
//security session에 들어가는 타입은 Authentication 타입의 객체여야 한다.
//Authentication 안에 User정보를 넣어야 함
//그 User 오브젝트 타입은 UserDetails 타입이어야 한다.
//즉 (Security ContextHolder(new Authentication(new User Details(new user)))
@Data
public class PrincipalDetails implements UserDetails,OAuth2User {

		private User user;	
		
		private Map<String,Object>attributes ;
		
		public PrincipalDetails(User user) {
			this.user = user;
			
			
		}
		public PrincipalDetails(User user, Map<String,Object>attributes ) {
			this.user = user;
			this.attributes = attributes;
			
		}
		
	
	// 권한 체크 하는 부분(hasRole(''))
	// 사용자마다 권한이 여러개일 수 있기때문에 Collection 형태임
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(()->user.getRoles());
		
		return collect;
	}

	// 패스워드
	@Override
	public String getPassword() {
		
		// spring security가 확인 가능한 password
		return user.getPassword();
	}

	//아이디=username
	@Override
	public String getUsername() {
		
		if(user !=null) {
			return user.getUsername();
		}else {
			return String.valueOf(attributes.get("id"));
		}	
	}
	
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() { // 활성화,비활성화
		//만약에 사이트에서 1년동안 로그인을 하지 않을 경우  휴면계정으로 전환하기로 했다면
		//현재시간 - 마지막 로그인 시간을 계산하여 1년 초과 시 return false로 돌리면 됨
		return true;
	}


	@Override
	public Map<String, Object> getAttributes() {
		
		return attributes;
	}


	@Override
	public String getName() {
		
		return user.getId()+"";
	}

}

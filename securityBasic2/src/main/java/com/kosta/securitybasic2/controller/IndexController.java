package com.kosta.securitybasic2.controller;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.securitybasic2.auth.PrincipalDetails;
import com.kosta.securitybasic2.entity.User;
import com.kosta.securitybasic2.repository.UserRepository;

@Controller
public class IndexController {

	@Autowired 
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping({"","/"})
	@ResponseBody
	public String index() {
		return "인덱스입니다.";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@PostMapping("/joinProc")
	public String joinProc(User user) {
		String rawPassword = user.getPassword();
		String encodingPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encodingPassword);
		userRepo.save(user);
		return "redirect:/";
	}
	
	//testing  config에서 설정한 /user,/admin,/manager  url은 각각 권한이 있는 사람만 들어 갈 수 있게 됨 
	// 즉 /admin url은 role이 admin과 manager만 들어갈 수 있고 user사용자는 접근 불가  에러난다.
	@GetMapping("/user")
	@ResponseBody
	public String user(@AuthenticationPrincipal  PrincipalDetails principal) {
			//security session에 저장된 user정보를 받아 사용할 수 있다 
			//이를 위하여 @AuthenticationPrincipal 사용하여 
			//PrincipalDetails 객체를(user) 받음
			User user = principal.getUser();
			
			
		return "유저입니다.";
	}
	
	
	
	 
	//권한이 두개 이상일 때 는 config에서 @EnableGlobalMethodSecurity(prePostEnabled = true) 으로 url에 권한을 설정해준다.
	//@PreAuthorize 는권한 1개 이상 설정할때 사용 가능하며 반드시 hasRole() 이렇게 사용해야함
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		return "어드민입니다.";
	}
	
	
	 //config에서 @EnableGlobalMethodSecurity(securedEnabled = true )설정 때문에 가능
	// @Secured는 권한 하나만 설정 가능
	@Secured("ROLE_MANAGER")
	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		return "매니저입니다.";
	}
	
}

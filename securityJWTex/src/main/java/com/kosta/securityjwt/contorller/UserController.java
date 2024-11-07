package com.kosta.securityjwt.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.securityjwt.auth.PrincipalDetails;
import com.kosta.securityjwt.entity.User;
import com.kosta.securityjwt.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired 
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/user")
	public ResponseEntity<User> user(Authentication authentication) {
		PrincipalDetails principalDetails =  (PrincipalDetails) authentication.getPrincipal();
		User user = principalDetails.getUser();
		System.out.println(user);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	
	@PostMapping("/join") 
	public ResponseEntity<String> join (User user) {
		String rawPassword = user.getPassword();
		String encodingPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encodingPassword);
		user.setRoles("ROLE_USER");
		userRepo.save(user);
		return new ResponseEntity<String>("true",HttpStatus.OK);
	}
	
}

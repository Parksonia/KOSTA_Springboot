package com.kosta.bankback.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.bankback.dto.MemberDto;
import com.kosta.bankback.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody MemberDto memberDto ) {
		try {
			memberService.join(memberDto);
			return new ResponseEntity<String>(String.valueOf(true),HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<MemberDto>login(@RequestBody Map<String,String> param) {
		try {
			String id = param.get("id");
			String password = param.get("password");
			MemberDto memberDto= memberService.login(id, password);
			return new ResponseEntity<MemberDto>(memberDto,HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<MemberDto>(HttpStatus.BAD_REQUEST);
			
		}
	}
}

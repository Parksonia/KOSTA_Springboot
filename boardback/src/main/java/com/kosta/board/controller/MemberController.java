package com.kosta.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.MemberDto;
import com.kosta.board.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@PostMapping("/join")
	public ResponseEntity<String> join(MemberDto memberDto,
			@RequestParam(name= "profile", required = false) MultipartFile file ) {
		try {
			memberService.join(memberDto,file);
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
	
	@GetMapping("/checkMemId/{id}")
	public ResponseEntity<String> checkMemId(@PathVariable String id) {
		try {
			boolean check =memberService.checkDoubleId(id);
			return new ResponseEntity<String>(String.valueOf(check),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	
}

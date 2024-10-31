package com.kosta.bankback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.bankback.dto.AccountDto;
import com.kosta.bankback.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping("/makeAccount")
	public ResponseEntity<String> makeAccount(@RequestBody AccountDto accountDto) {
		try {
			accountService.makeAccount(accountDto);
			return new ResponseEntity<String>("true",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("false",HttpStatus.BAD_REQUEST);
		}
	}
}

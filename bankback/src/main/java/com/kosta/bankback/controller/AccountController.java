package com.kosta.bankback.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.bankback.dto.AccountDto;
import com.kosta.bankback.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@GetMapping("/checkAccId/{id}")
	public ResponseEntity<String> checkAccId(@PathVariable String id) {
		try {
			boolean check =accountService.doubleId(id);
			return new ResponseEntity<String>(String.valueOf(check),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PostMapping("/makeAccount")
	public ResponseEntity<String> makeAccount(@RequestBody AccountDto accountDto) { //Axios는 자동으로 JSOn을 알아서 형변환 해줌 
		try {
			accountService.makeAccount(accountDto);
			return new ResponseEntity<String>("true",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("false",HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/deposit")
	public ResponseEntity<String>deposit(@RequestBody Map<String,String>param) { // 따로 객체가 아니라 여러개의 파라미터를 받기 때문에 Map으로 받아옴
		try {
			String id = (String)param.get("id");
			Integer money = Integer.parseInt(param.get("money"));
			AccountDto accDto = accountService.deposit(id, money);
			return new ResponseEntity<String>(accDto.getBalance()+"",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("/withdraw")
	public ResponseEntity<String>withdraw(@RequestBody Map<String,String> param){
	
		try {
			String id = (String)param.get("id");
			Integer money = Integer.parseInt(param.get("money")); 
			AccountDto accDto = accountService.withdraw(id, money);
			return new ResponseEntity<String>(accDto.getBalance()+"",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/accountInfo/{id}")
	public ResponseEntity<AccountDto>accountInfo(@PathVariable String id){
		
		try {
			
			AccountDto accDto = accountService.accountinfo(id);
			return new ResponseEntity<AccountDto>(accDto,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<AccountDto>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/allAccountInfo")
	public ResponseEntity<List<AccountDto>>allAccountInfo(){
		
		try {
			
			List<AccountDto> accDtoList = accountService.allacountinfo();
		
			
			return new ResponseEntity<List<AccountDto>>(accDtoList,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<AccountDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/transfer")
	public ResponseEntity<String> transfer(@RequestBody Map <String,String>param){
		try {
			String sid =param.get("sid");
			String rid = param.get("rid");
			Integer money = Integer.parseInt(param.get("money"));
			System.out.println(sid);
			System.out.println(rid);
			System.out.println(money);
			accountService.transfer(sid, rid, money);
			AccountDto acc = accountService.accountinfo(sid);
			return new ResponseEntity<String>(acc.getBalance()+"",HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
}

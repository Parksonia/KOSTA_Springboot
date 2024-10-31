package com.kosta.bankback.service;

import java.util.List;

import com.kosta.bankback.dto.AccountDto;



public interface AccountService {
	AccountDto makeAccount(AccountDto acc) throws Exception;
	AccountDto deposit(String id, Integer money) throws Exception;
	AccountDto withdraw(String id,Integer money) throws Exception;
	AccountDto accountinfo(String id) throws Exception;
	List<AccountDto> allacountinfo() throws Exception;
	void transfer(String sid,String rid,Integer money) throws Exception;
	boolean doubleId(String id) throws Exception;
}

package com.kosta.bankjpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.bankjpa.entity.Account;
import com.kosta.bankjpa.respository.AccountRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	@Autowired
	private final AccountRepository accountRepo;
	
	@Override
	public Account makeAccount(Account acc) throws Exception {
		
		return accountRepo.save(acc);
	}

	@Override
	public Account deposit(String id, Integer money) throws Exception {
		
		
		Account acc = accountRepo.findById(id).orElseThrow(()->new Exception("계좌번호오류"));
		acc.deposit(money);
		accountRepo.save(acc);
		return acc;
	}

	@Override
	public Account withdraw(String id, Integer money) throws Exception {
		Account acc = accountRepo.findById(id).orElseThrow(()->new Exception("계좌번호오류"));
		acc.withdraw(money);
		accountRepo.save(acc);
		return acc;
	}

	@Override
	public Account accountinfo(String id) throws Exception {
		Account findAccount = accountRepo.findById(id).orElseThrow(()->new Exception("계좌번호 오류"));
		
		return findAccount;
	}

	@Override
	public List<Account> allacountinfo() throws Exception {
		List<Account> accountList=accountRepo.findAll();	
		return accountList;
	}

	@Override
	public void transfer(String sid, String rid, Integer money) throws Exception {
		Account sAccount = accountRepo.findById(sid).orElseThrow(()->new Exception("보내는계좌번호 오류"));
		Account rAccount = accountRepo.findById(rid).orElseThrow(()->new Exception("받는계좌번호 오류"));
		
		sAccount.withdraw(money);
		accountRepo.save(sAccount);
		rAccount.deposit(money);
		accountRepo.save(rAccount);

	}

	@Override
	public boolean doubleId(String id) throws Exception {
		
		Optional<Account> checkId= accountRepo.findById(id);
		if(checkId.isPresent())return true;
		
		return false;
	}

}

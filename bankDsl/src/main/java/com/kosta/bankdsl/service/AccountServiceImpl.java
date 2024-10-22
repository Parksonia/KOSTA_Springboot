package com.kosta.bankdsl.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.bankdsl.dto.AccountDto;
import com.kosta.bankdsl.entity.Account;
import com.kosta.bankdsl.repository.AccountRepository;
import com.kosta.bankdsl.repository.BankRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private BankRepository bankRepo;

	
	@Override
	public AccountDto makeAccount(AccountDto accDto) throws Exception {
		 Account acc = bankRepo.findAccountById(accDto.getId());
		 if(acc !=null) throw new Exception("중복계좌오류");
		 
		 bankRepo.insertAccount(accDto.toEntity());;
		 return accDto;
	}

	@Override
	public AccountDto deposit(String id, Integer money) throws Exception {
		Account acc = bankRepo.findAccountById(id);
		if(acc == null )throw new Exception("계좌번호 오류");
		acc.deposit(money);
		bankRepo.updateBalance(id, acc.getBalance());
		
		return acc.toDto();
	}

	@Override
	public AccountDto withdraw(String id, Integer money) throws Exception {
		Account acc = bankRepo.findAccountById(id);
		if(acc == null )throw new Exception("계좌번호 오류");
		acc.withdraw(money);
		bankRepo.updateBalance(id, acc.getBalance());
		
		return acc.toDto();
	}

	@Override
	public AccountDto accountinfo(String id) throws Exception {
	
		return bankRepo.findAccountById(id).toDto();
	}

	@Override
	public List<AccountDto> allacountinfo() throws Exception {
		
		return bankRepo.findAllAccount().stream()
				.map(a->a.toDto()).collect(Collectors.toList());
	}

	@Override
	public void transfer(String sid, String rid, Integer money) throws Exception {
		
		Account sacc = bankRepo.findAccountById(sid);
		if(sacc ==null) throw new Exception("보내는계좌 오류");
		sacc.withdraw(money);
		
		Account racc = bankRepo.findAccountById(rid);
		if(racc ==null) throw new Exception("받는계좌 오류");
		racc.deposit(money);
		bankRepo.transfer(sid, rid, sacc.getBalance(), racc.getBalance());

	}

	@Override
	public boolean doubleId(String id) throws Exception {
		return bankRepo.findAccountById(id)!= null;
		
	}

}

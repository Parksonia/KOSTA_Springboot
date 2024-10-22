package com.kosta.bankdsl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.bankdsl.dto.MemberDto;
import com.kosta.bankdsl.entity.Member;
import com.kosta.bankdsl.repository.BankRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private BankRepository bankRepo;
	
	
	@Override
	public void join(MemberDto memberDto) throws Exception {
		bankRepo.insertMember(memberDto.toEntity());

	}

	@Override
	public MemberDto login(String id, String password) throws Exception {
		Member member = bankRepo.findMemberById(id);
		if(!member.getPassword().equals(password)) throw new Exception("비밀번호 오류");
		return member.toDto();
	}

	@Override
	public boolean checkDoubleId(String id) throws Exception {
		return bankRepo.existMemberId(id);	
	}

}

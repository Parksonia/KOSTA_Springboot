package com.kosta.bankjpa.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kosta.bankjpa.entity.Member;
import com.kosta.bankjpa.respository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepo;

	@Override
	public void join(Member member) throws Exception {
		memberRepo.save(member);
		
	}

	@Override
	public Member login(String id, String password) throws Exception {
		Member lMember = memberRepo.findById(id).orElseThrow(()->new Exception("아이디오류"));
		if(!lMember.getPassword().equals(password))throw new Exception("비밀번호오류");
		return lMember;
	}

	@Override
	public boolean checkDoubleId(String id) throws Exception {

		Optional<Member> checkMember = memberRepo.findById(id);
		if(checkMember.isPresent()) return true;
		return false;
	}




}

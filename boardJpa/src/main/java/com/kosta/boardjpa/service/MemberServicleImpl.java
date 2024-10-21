package com.kosta.boardjpa.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kosta.boardjpa.dto.MemberDto;
import com.kosta.boardjpa.entity.Member;
import com.kosta.boardjpa.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServicleImpl implements MemberService {

	private final MemberRepository memberRepo;
	
	@Override
	public void join(MemberDto member) throws Exception {
		memberRepo.save(member.toEntity());
	}

	@Override
	public MemberDto login(String id, String password) throws Exception {
		Member loginMember = memberRepo.findById(id).orElseThrow(()->new Exception("아이디오류"));
		if(!password.equals(loginMember.getPassword())) throw new Exception("비밀번호 에러");
		return loginMember.toDto();
	}

	@Override
	public boolean checkDoubleId(String id) throws Exception {
		
		return memberRepo.findById(id).isPresent();
	}

}

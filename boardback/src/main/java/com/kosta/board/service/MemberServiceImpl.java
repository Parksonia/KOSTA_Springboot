package com.kosta.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.MemberDto;
import com.kosta.board.entity.Board;
import com.kosta.board.entity.Member;
import com.kosta.board.repository.MemberRespository;

@Service
public class MemberServiceImpl implements MemberService {

@Autowired	
private MemberRespository memberRepo;
	
	@Override
	public void join(MemberDto memberDto,MultipartFile profile) throws Exception {
		
		// 컨트롤러에서 가입시 ajax로 중복체크 처리를 하더라도 서비스에서도 중복체크를 한번 더 해주는게 좋다. 
		if(memberRepo.findById(memberDto.getId()).isPresent()) throw new Exception("아이디 중복 오류");
		Member memberEntity = memberDto.toEntity();
	
		if(profile!=null) {
			memberEntity.setProfileImage(profile.getBytes());
		}
		memberRepo.save(memberEntity);
	}

	@Override
	public MemberDto login(String id, String password) throws Exception {
		
		Member loginMember = memberRepo.findById(id).orElseThrow(()->new Exception("아이디오류"));
		
		// 입력받은 비밀번호가 일치 하지 않을 수 있음.
		if(!password.equals(loginMember.getPassword())) throw new Exception("비밀번호 에러");
		return loginMember.toDto();
	}

	@Override
	public boolean checkDoubleId(String id) throws Exception {
		
		return memberRepo.findById(id).isPresent();
	}

}

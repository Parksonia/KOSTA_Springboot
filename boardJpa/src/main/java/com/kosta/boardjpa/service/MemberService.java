package com.kosta.boardjpa.service;

import com.kosta.boardjpa.dto.MemberDto;

 
public interface MemberService {
	void join(MemberDto member) throws Exception;
	MemberDto login(String id, String password) throws Exception;
	boolean checkDoubleId(String id) throws Exception;
}

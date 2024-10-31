package com.kosta.bankback.service;

import com.kosta.bankback.dto.MemberDto;

public interface MemberService {

	void join(MemberDto member) throws Exception;
	MemberDto login(String id,String password) throws Exception;
	boolean checkDoubleId(String id) throws Exception;
}

package com.kosta.bankdsl.service;

import com.kosta.bankdsl.dto.MemberDto;

public interface MemberService {

	void join(MemberDto member) throws Exception;
	MemberDto login(String id,String password) throws Exception;
	boolean checkDoubleId(String id) throws Exception;
}

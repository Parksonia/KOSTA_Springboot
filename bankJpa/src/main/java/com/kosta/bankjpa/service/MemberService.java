package com.kosta.bankjpa.service;

import com.kosta.bankjpa.entity.Member;

public interface MemberService {

	void join(Member member) throws Exception;
	Member login(String id,String password) throws Exception;
	boolean checkDoubleId(String id) throws Exception;
}

package com.kosta.bankjpa.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.bankjpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}

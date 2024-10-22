package com.kosta.bankdsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.bankdsl.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}

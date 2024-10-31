package com.kosta.bankback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.bankback.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}

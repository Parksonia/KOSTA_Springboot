package com.kosta.boardjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.boardjpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}

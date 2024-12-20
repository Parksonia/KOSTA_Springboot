package com.kosta.boarddsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.boarddsl.entity.Member;

public interface MemberRespository extends JpaRepository<Member, String> {

}

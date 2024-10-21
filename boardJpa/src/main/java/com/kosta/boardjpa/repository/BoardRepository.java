package com.kosta.boardjpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.boardjpa.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	//제목 검색
	Page<Board> findBySubjectContains(String subject,PageRequest pageRequest);
	// 내용 검색
	Page<Board> findByContentContains(String content,PageRequest pageRequest);
	//작성자 검색
	Page<Board> findByMember_Nickname(String writer,PageRequest pageRequest);
}

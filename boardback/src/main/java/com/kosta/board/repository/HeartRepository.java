package com.kosta.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.board.entity.Heart;

public interface HeartRepository extends JpaRepository<Heart, Integer> {
	void deleteByBoardNum(Integer boardNum);
}

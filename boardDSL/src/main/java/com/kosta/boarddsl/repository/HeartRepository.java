package com.kosta.boarddsl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.boarddsl.entity.Heart;

public interface HeartRepository extends JpaRepository<Heart, Integer> {
	void deleteByBoardNum(Integer boardNum);
}

package com.kosta.boarddsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.boarddsl.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}

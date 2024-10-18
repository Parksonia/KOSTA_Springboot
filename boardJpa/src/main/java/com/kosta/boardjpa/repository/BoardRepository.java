package com.kosta.boardjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.boardjpa.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}

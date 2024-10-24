package com.kosta.boarddsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.boarddsl.entity.BFile;

public interface FileRepository extends JpaRepository<BFile, Integer> {

}
	
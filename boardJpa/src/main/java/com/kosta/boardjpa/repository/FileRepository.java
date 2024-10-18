package com.kosta.boardjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.boardjpa.entity.BFile;

public interface FileRepository extends JpaRepository<BFile, Integer> {

}

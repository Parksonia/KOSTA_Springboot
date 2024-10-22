package com.kosta.univdsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.univdsl.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

}

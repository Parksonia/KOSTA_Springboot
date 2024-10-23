package com.kosta.univdsl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.univdsl.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
	
	List<Professor> findByPosition(String position);
	Optional<Professor>findByNameAndDeptno(String profName,Integer deptNo); 
}

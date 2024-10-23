package com.kosta.univdsl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.univdsl.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	Optional<Department>findByDname(String dName);
	List<Department>findByPart(Integer part);
	List<Department>findByBuild(String build);
}

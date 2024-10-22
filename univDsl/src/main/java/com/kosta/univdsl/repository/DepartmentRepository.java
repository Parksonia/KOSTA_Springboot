package com.kosta.univdsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.univdsl.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}

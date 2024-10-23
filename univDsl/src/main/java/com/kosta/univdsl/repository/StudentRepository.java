package com.kosta.univdsl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.univdsl.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	List<Student> findByName(String studName);
	List<Student> findByGrade(Integer grade);


}

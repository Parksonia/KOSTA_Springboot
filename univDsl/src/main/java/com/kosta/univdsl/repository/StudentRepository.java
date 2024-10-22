package com.kosta.univdsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.univdsl.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}

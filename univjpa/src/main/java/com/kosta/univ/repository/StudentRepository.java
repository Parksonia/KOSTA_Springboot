package com.kosta.univ.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.univ.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findByName(String studName);
	List<Student> findByGrade(Integer grade);
	Optional<Student> findByJumin(String jumin);
	
	// 명명 규칙에 따라 student deptno1 의 경우는 department의 dept를 참조하는것이므로
	//findBy + 참조한 Entity변수명 + _ +참조한Entity의 컬럼명으로 작성하면 된다.
	List<Student> findByDepartment1_Deptno(Integer deptNo1);  
	List<Student>findByDepartment1_Dname(String deptName);

	List<Student> findByDepartment2_Deptno(Integer deptNo2);  
	List<Student>findByDepartment2_Dname(String deptName);
	//null인 값 조회
	List<Student> findByProfessor_ProfNoIsNull();

	
}

package com.kosta.univ.dto;

import java.sql.Date;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {

	//profNo prfessor 참조 함
	
	
	private Integer studNo;	
	private String name;
	private String id;
	private Integer grade;
	private String jumin;
	private Date birthday;
	private String tel;
	private Integer height;
	private Integer weight;
	private Integer deptNo1;
	private String dname1;
	private Integer deptNo2;
	private String dname2;
	private Integer profNo;
	private String profName;

	public Student toEntity() {
		
		
		Student student = Student.builder()
					.studNo(studNo)
					.name(name)
					.id(id)
					.grade(grade)
					.jumin(jumin)
					.birthday(birthday)
					.tel(tel)
					.height(height)
					.weight(weight)
					.build();
					//null인 경우에도 insert될 수 있어야 함
					if(deptNo1 !=null) {
						student.setDepartment1(Department.builder().deptno(deptNo1).dname(dname1).build());	
					}
					if(deptNo2 !=null) {
						student.setDepartment2(Department.builder().deptno(deptNo2).dname(dname2).build());
					}
					if(profNo !=null) {
						student.setProfessor(Professor.builder().profNo(profNo).name(profName).build());
					}
					
		return student;
	}
}

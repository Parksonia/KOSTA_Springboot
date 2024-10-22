package com.kosta.univdsl.dto;

import java.sql.Date;

import com.kosta.univdsl.entity.Department;
import com.kosta.univdsl.entity.Professor;
import com.kosta.univdsl.entity.Student;

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
		
		
		return Student.builder()
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
		
	}
}

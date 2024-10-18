package com.kosta.univ.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.kosta.univ.dto.StudentDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
	
	@Id
	private Integer studNo;

	@Column
	private String name;
	@Column(unique = true)
	private String id;
	@Column
	private Integer grade;
	@Column
	private String jumin;
	@Column
	private Date birthday;
	@Column
	private String tel;
	@Column
	private Integer height;
	@Column
	private Integer weight;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="profNo")
	private Professor professor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="deptNo1")
	private Department department1;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="deptNo2")
	private Department department2;

	@Override
	public String toString() {
		return "Student [studNo=" + studNo + ", name=" + name + ", id=" + id + ", grade=" + grade + ", jumin=" + jumin
				+ ", birthday=" + birthday + ", tel=" + tel + ", height=" + height + ", weight=" + weight
				+ ", professor=" + professor + ", department1=" + department1 + ", department2=" + department2 + "]";
	}
	
	public StudentDto toDto() {
		StudentDto studentDto = StudentDto.builder()
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
		  
		if (department1 != null) {
		        if (department1.getDeptno() != null) {
		            studentDto.setDeptNo1(department1.getDeptno());
		        }
		        if (department1.getDname() != null) {
		            studentDto.setDname1(department1.getDname());
		        }
		    }

		    if (department2 != null) {
		        if (department2.getDeptno() != null) {
		            studentDto.setDeptNo2(department2.getDeptno());
		        }
		        if (department2.getDname() != null) {
		            studentDto.setDname2(department2.getDname());
		        }
		    }

		    if (professor != null) {
		        if (professor.getProfNo() != null) {
		            studentDto.setProfNo(professor.getProfNo());
		        }
		        if (professor.getName() != null) {
		            studentDto.setProfName(professor.getName());
		        }
		    }


		 return studentDto;
	}
	
}

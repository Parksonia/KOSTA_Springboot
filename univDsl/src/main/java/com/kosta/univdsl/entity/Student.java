package com.kosta.univdsl.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.kosta.univdsl.dto.StudentDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
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
	
	@Column
	private Integer profNo;
	
	@Column
	private Integer deptNo1;
	
	@Column
	private Integer deptNo2;

	
	
	
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
				.deptNo1(deptNo1)
				.deptNo2(deptNo2)
				.profNo(profNo)
				.build();


		 return studentDto;
	}
	
}

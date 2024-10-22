package com.kosta.univdsl.dto;

import java.sql.Date;

import com.kosta.univdsl.entity.Department;
import com.kosta.univdsl.entity.Professor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessorDto {

	
	private Integer profNo;
	private String name;	
	private String id; 
	private String position; 
	private Integer pay; 
	private Date hiredate;
	private Integer bonus;
	private String email;
	private String hpage;
	private Integer deptno;
	private String dname;
	
	
	public Professor toEntity() {
		return Professor.builder()
					.profNo(profNo)
					.name(name)
					.id(id)
					.position(position)
					.pay(pay)
					.hiredate(hiredate)
					.bonus(bonus)
					.email(email)
					.hpage(hpage)
					.deptno(deptno)
					.build();
	}
	
	
	
}

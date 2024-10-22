package com.kosta.univdsl.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.kosta.univdsl.dto.ProfessorDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Professor {

	@Id
	private Integer profNo;
	
	@Column
	private String name;
	@Column
	private String id; 
	@Column
	private String position; 
	@Column
	private Integer pay; 
	@Column
	private Date hiredate;
	@Column
	private Integer bonus;
	@Column
	private String email;
	@Column
	private String hpage;

	@Column 
	private Integer deptno;
	
	public ProfessorDto toDto() {
		return ProfessorDto.builder()
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

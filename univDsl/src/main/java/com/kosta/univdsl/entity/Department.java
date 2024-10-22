package com.kosta.univdsl.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.kosta.univdsl.dto.DepartmentDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Department {

	@Id
	private Integer deptno;
	@Column 
	private String dname;
	@Column
	private Integer part;
	@Column
	private String build;

	
	
	public DepartmentDto toDto() {
		return DepartmentDto.builder()
					.deptno(deptno)
					.dname(dname)
					.part(part)
					.build(build)
					.build();
	}
	
}

package com.kosta.univdsl.dto;



import com.kosta.univdsl.entity.Department;

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
public class DepartmentDto {

	
	private Integer deptno;
	 
	private String dname;
	
	private Integer part;
	
	private String build;

	public Department toEntity() {
		return Department.builder()
					.deptno(deptno)
					.dname(dname)
					.part(part)
					.build(build)
					.build();
	}
	
}

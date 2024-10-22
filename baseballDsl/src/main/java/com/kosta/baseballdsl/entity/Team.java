package com.kosta.baseballdsl.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.kosta.baseballdsl.dto.TeamDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//1:N로 테이블의 관계에서는 ToString을 쓰지 않는다.
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	private String name;
	private String loc;

	
	public TeamDto toDto() {
		return TeamDto.builder().num(num)
				.name(name)
				.loc(loc).build();
	}
	
}

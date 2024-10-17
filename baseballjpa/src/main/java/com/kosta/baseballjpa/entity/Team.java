package com.kosta.baseballjpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kosta.baseballjpa.dto.TeamDto;

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

	//Player에서 선언한 private Team team; 와 mapped 해줘야함
	//team(1): player(N) 현재Entity를 기준으로 한다.
	//@JsonManagedReference 순환 참조 문제 해결 
	/* @JsonManagedReference */
	@OneToMany(mappedBy = "team",fetch = FetchType.LAZY)
	private List<Player> playerList = new ArrayList<>();
	
	
	public TeamDto toDto() {
		return TeamDto.builder().num(num)
				.name(name)
				.loc(loc).build();
	}
	
}

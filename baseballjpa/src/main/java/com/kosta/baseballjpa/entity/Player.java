package com.kosta.baseballjpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kosta.baseballjpa.dto.PlayerDto;

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
public class Player {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	
	@Column
	private String name;

	@Column
	private Integer backNum;
	
	/*
	 * teamNum을 가지고 team을 조회하게 됨 p.teamNum = t.teamNum 따라서 JoinColumn으로 설정 해주고 지워야함
	 * @Column private Integer teamNum;
	 */
	
	//선수는 특정 한 팀에 소속됨 (1:N-> 팀(1):선수들(N))
	//Entity에서만 관계가 생기게 되는 것 
	/* @JsonBackReference */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="teamNum")
	private Team team;

	
	
	//Entity를 Dto로 바꾸는 메서드 추가 
	public PlayerDto toDto() {
		return PlayerDto.builder().num(num)
				.name(name)
				.backNum(backNum)
				.teamNum(team.getNum())
				.teamName(team.getName())
				.teamLoc(team.getLoc())
				.build();
	}
		
}

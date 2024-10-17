package com.kosta.baseballjpa.dto;

import com.kosta.baseballjpa.entity.Player;
import com.kosta.baseballjpa.entity.Team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDto {

	private Integer num;
	private String name;
	private Integer backNum;
	private Integer teamNum;
	private String teamName;
	private String teamLoc;
	
	//Entity로 바꾸는 메서드 생성 
	public Player toEntity() { 
		return Player.builder().num(num)
				.name(name)
				.backNum(backNum)
				.team(Team.builder().num(teamNum).name(teamName).loc(teamLoc).build())
				.build();
	}
	
}

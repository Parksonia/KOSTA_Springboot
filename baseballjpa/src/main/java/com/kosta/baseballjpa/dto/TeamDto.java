package com.kosta.baseballjpa.dto;

import com.kosta.baseballjpa.entity.Team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamDto {

	private Integer num;
	private String name;
	private String loc;
	
	public Team toEntity() {
		return Team.builder().num(num)
				.name(name)
				.loc(loc).build();
	}
}

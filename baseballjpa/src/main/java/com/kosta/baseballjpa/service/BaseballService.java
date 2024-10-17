package com.kosta.baseballjpa.service;

import java.util.List;

import com.kosta.baseballjpa.dto.PlayerDto;
import com.kosta.baseballjpa.dto.TeamDto;
import com.kosta.baseballjpa.entity.Player;
import com.kosta.baseballjpa.entity.Team;

public interface BaseballService {
	
	
	void registTeam(TeamDto teamDto) throws Exception;
	TeamDto teamInfoByName (String teamName) throws Exception;
	TeamDto teamInfoByNum(Integer teamNum) throws Exception;
	List<TeamDto> teamListByLoc(String loc) throws Exception;
	void registPlayerWithTeamName(PlayerDto playerDto) throws Exception;
	void registPlayerWithTeamNum(PlayerDto playerDto) throws Exception;
	PlayerDto playerInfoByNum(Integer num) throws Exception;
	List<PlayerDto>playerInfoByName (String name) throws Exception;
	List<PlayerDto>playerListInTeamByTeamNum(Integer teamNum) throws Exception;
	List<PlayerDto>playerListInTeamByTeamName(String teamName) throws Exception;

}

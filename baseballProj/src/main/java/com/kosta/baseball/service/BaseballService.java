package com.kosta.baseball.service;

import java.util.List;

import com.kosta.baseball.dto.Player;
import com.kosta.baseball.dto.Team;

public interface BaseballService {
	
	
	void registTeam(Team team) throws Exception;
	Team teamInfoByName (String teamName) throws Exception;
	Team teamInfoByNum(Integer teamNum) throws Exception;
	List<Team> teamListByLoc(String loc) throws Exception;
	void registPlayerWithTeamName(Player player) throws Exception;
	void registPlayerWithTeamNum(Player player) throws Exception;
	Player playerInfoByNum(Integer num) throws Exception;
	List<Player>playerInfoByName (String name) throws Exception;
	List<Player>playerListInTeamByTeamNum(Integer teamNum) throws Exception;
	List<Player>playerListInTeamByTeamName(String teamName) throws Exception;

}

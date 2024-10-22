package com.kosta.baseballdsl.service;

import java.util.List;

import com.kosta.baseballdsl.dto.PlayerDto;
import com.kosta.baseballdsl.dto.TeamDto;

public interface BaseballService {
	
	//1.팀등록
	void registTeam(TeamDto teamDto) throws Exception;
	//2.팀 이름으로 팀 조회
	TeamDto teamInfoByName (String teamName) throws Exception;
	//3. 팀 번호로 팀 조회
	TeamDto teamInfoByNum(Integer teamNum) throws Exception;
	//4. 지역으로 팀 조회
	List<TeamDto> teamListByLoc(String loc) throws Exception;
	//5. 선수 등록(팀 이름으로)
	void registPlayerWithTeamName(PlayerDto playerDto) throws Exception;
	//6. 선수 등록(팀 번호로)
	void registPlayerWithTeamNum(PlayerDto playerDto) throws Exception;
	//7. 선수조회(선수 번호로,팀 이름 포함)
	PlayerDto playerInfoByNum(Integer num) throws Exception;
	//8. 선수 목록 조회
	List<PlayerDto>playerInfoByName (String name) throws Exception;
	//9. 선수 목록 조회
	List<PlayerDto>playerListInTeamByTeamNum(Integer teamNum) throws Exception;
	//10. 선수 목록 조회
	List<PlayerDto>playerListInTeamByTeamName(String teamName) throws Exception;

}

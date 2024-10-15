package com.kosta.baseball.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kosta.baseball.dto.Player;
import com.kosta.baseball.dto.Team;

@Mapper
@Repository
public interface BaseballDao {
	

    // 1. 팀등록
	void registTeam(Team team) throws Exception;
	// 2. 팀 이름으로 팀조회
	Team selectTeamParamName(@Param("name")String name) throws Exception;
	// 3. 팀 번호로 팀 조회
	Team selectTeamParamNum(@Param("num")Integer num) throws Exception;
	// 4. 지역으로 팀 조회
	List<Team> selectTeamParamLoc(@Param("loc")String loc)throws Exception;
	
	// 5. 선수 등록(팀이름으로) ,6. 선수 등록(팀번호로)
	void registPlayer(Player player) throws Exception;
	
	// 7. 선수 조회(선수번호로, 팀이름포함) 
	Player selectPlayerByNum(@Param("num")Integer num) throws Exception;
	
	// 8. 선수 조회(선수이름으로, 팀이름포함) 
	List<Player> selectPlayerByName(@Param("name")String name) throws Exception;
	
	// 9. 특정 선수 목록 조회(팀번호로) 
	List<Player>selectPlayerListByTeamNum(@Param("teamNum")Integer teamNum) throws Exception;
	
	// 10. 특정팀 선수 목록 조회(팀이름으로) 
	List<Player> selectPlayerListByTeamName(@Param("teamName")String teamName) throws Exception;
	
	
	
}

package com.kosta.baseball.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.baseball.dao.BaseballDao;
import com.kosta.baseball.dto.Player;
import com.kosta.baseball.dto.Team;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BaseBallServiceImpl implements BaseballService {

	private final BaseballDao baseballDao;
	
	


	@Override
	public void registTeam(Team team) throws Exception {
		baseballDao.registTeam(team);
		
	}


	@Override
	public Team teamInfoByName(String teamName) throws Exception {		
		return baseballDao.selectTeamParamName(teamName);
	}


	@Override
	public Team teamInfoByNum(Integer teamNum) throws Exception {
		return baseballDao.selectTeamParamNum(teamNum);
	}


	@Override
	public List<Team> teamListByLoc(String loc) throws Exception {
		return baseballDao.selectTeamParamLoc(loc);
	}

	

	@Override
	public void registPlayerWithTeamName(Player player) throws Exception {
		Team team = baseballDao.selectTeamParamName(player.getTeamName());
		player.setTeamNum(team.getNum());
		baseballDao.insertPlayer(player);
	}


	@Override
	public void registPlayerWithTeamNum(Player player) throws Exception {
		
		
	}


	@Override
	public Player playerInfoByNum(Integer num) throws Exception {
		
		return null;
	}


	@Override
	public List<Player> playerInfoByName(String name) throws Exception {
		
		return null;
	}


	@Override
	public List<Player> playerListInTeamByTeamNum(Integer teamNum) throws Exception {
		
		return null;
	}


	@Override
	public List<Player> playerListInTeamByTeamName(String teamName) throws Exception {
		
		return null;
	}
	
}

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
		System.out.println(team.getNum());
		player.setTeamNum(team.getNum());
		
		baseballDao.registPlayer(player);
	}


	@Override
	public void registPlayerWithTeamNum(Player player) throws Exception {
		baseballDao.registPlayer(player);
		
	}


	@Override
	public Player playerInfoByNum(Integer num) throws Exception {	
		return baseballDao.selectPlayerByNum(num);
	}


	@Override
	public List<Player> playerInfoByName(String name) throws Exception {
		
		return baseballDao.selectPlayerByName(name);
	}


	@Override
	public List<Player> playerListInTeamByTeamNum(Integer teamNum) throws Exception {
		
		return baseballDao.selectPlayerListByTeamNum(teamNum);
	}


	@Override
	public List<Player> playerListInTeamByTeamName(String teamName) throws Exception {
		
		return baseballDao.selectPlayerListByTeamName(teamName);
	}
	
}

package com.kosta.baseballdsl.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.baseballdsl.dto.PlayerDto;
import com.kosta.baseballdsl.dto.TeamDto;
import com.kosta.baseballdsl.entity.Player;
import com.kosta.baseballdsl.entity.Team;
import com.kosta.baseballdsl.repository.BaseballRepository;
import com.querydsl.core.Tuple;

@Service
public class BaseballServiceImpl implements BaseballService {

	@Autowired 
	private BaseballRepository baseballRepo;
	
	@Override
	public void registTeam(TeamDto teamDto) throws Exception {
		//insert는 dsl 사용x,파라미터만 넘기고.save해줌
		baseballRepo.insertTeam(teamDto.toEntity());
		

	}

	@Override
	public TeamDto teamInfoByName(String teamName) throws Exception {
		// 쿼리dsl사용 	
		return baseballRepo.selectTeamByName(teamName).toDto();
	}

	@Override
	public TeamDto teamInfoByNum(Integer teamNum) throws Exception {
		
		return baseballRepo.selectTeamByNum(teamNum).toDto();
	}

	@Override
	public List<TeamDto> teamListByLoc(String loc) throws Exception {
		
		return baseballRepo.selectTeamByLoc(loc).stream()
				.map((t)->t.toDto()).collect(Collectors.toList());
	}

	@Override
	public void registPlayerWithTeamName(PlayerDto playerDto) throws Exception {
		//쿼리dsl 당 하나의 쿼리 사용 위해 따로 조회
		Team team = baseballRepo.selectTeamByName(playerDto.getTeamName());
		if(team ==null ) throw new Exception("팀 이름 오류");
		playerDto.setTeamNum(team.getNum());
		baseballRepo.insertPlayer(playerDto.toEntity());

	}

	@Override
	public void registPlayerWithTeamNum(PlayerDto playerDto) throws Exception {
		baseballRepo.insertPlayer(playerDto.toEntity());

	}

	@Override
	public PlayerDto playerInfoByNum(Integer num) throws Exception {
		Tuple tuple = baseballRepo.selectPlayerByNum(num);
		PlayerDto playerDto = tuple.get(0, Player.class).toDto();
		playerDto.setTeamName(tuple.get(1, String.class));
		playerDto.setTeamLoc(tuple.get(2, String.class));
		return playerDto;
	}

	@Override
	public List<PlayerDto> playerInfoByName(String name) throws Exception {
		List<Tuple> tupleList= baseballRepo.selectPlayerByName(name);
		
		return tupleList.stream().map(t->{
			PlayerDto playerDto = t.get(0,Player.class).toDto();
			playerDto.setTeamName(t.get(1, String.class));
			playerDto.setTeamLoc(t.get(2, String.class));
			return playerDto;
		}).collect(Collectors.toList());

	}

	@Override
	public List<PlayerDto> playerListInTeamByTeamNum(Integer teamNum) throws Exception {
		List<Tuple> tupleList= baseballRepo.selectPlayerListByTeamNum(teamNum);
		return tupleList.stream().map(t->{
			PlayerDto playerDto = t.get(0,Player.class).toDto();
			playerDto.setTeamName(t.get(1, String.class));
			playerDto.setTeamLoc(t.get(2, String.class));
			return playerDto;
		}).collect(Collectors.toList());

	}

	@Override
	public List<PlayerDto> playerListInTeamByTeamName(String teamName) throws Exception {

		List<Tuple> tupleList= baseballRepo.slectPlayerListByTeamName(teamName);
		return tupleList.stream().map(t->{
			PlayerDto playerDto = t.get(0,Player.class).toDto();
			playerDto.setTeamName(t.get(1, String.class));
			playerDto.setTeamLoc(t.get(2, String.class));
			return playerDto;
		}).collect(Collectors.toList());
	
	}

}

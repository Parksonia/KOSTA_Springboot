package com.kosta.baseballjpa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kosta.baseballjpa.dto.PlayerDto;
import com.kosta.baseballjpa.dto.TeamDto;
import com.kosta.baseballjpa.entity.Player;
import com.kosta.baseballjpa.entity.Team;
import com.kosta.baseballjpa.repository.PlayerRespository;
import com.kosta.baseballjpa.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BaseballServiceImpl implements BaseballService {

	private final PlayerRespository playerRepo;
	private final TeamRepository teamRepo;
	
	@Override
	public void registTeam(TeamDto teamDto) throws Exception {
		teamRepo.save(teamDto.toEntity());
	}

	@Override
	public TeamDto teamInfoByName(String teamName) throws Exception {		
		Team team = teamRepo.findByName(teamName).orElseThrow(()->new Exception("팀 오류")); 
		return team.toDto();
	}

	@Override
	public TeamDto teamInfoByNum(Integer teamNum) throws Exception {
		Team team = teamRepo.findById(teamNum).orElseThrow(()->new Exception("팀번호 오류")); 
		return team.toDto();
	}

	@Override
	public List<TeamDto> teamListByLoc(String loc) throws Exception {
		
		return teamRepo.findByLoc(loc).stream()
				.map((t)->t.toDto()).collect(Collectors.toList());
	}

	@Override
	public void registPlayerWithTeamName(PlayerDto playerDto) throws Exception {
		Team team = teamRepo.findByName(playerDto.getTeamName()).orElseThrow(()->new Exception("팀 오류"));
		playerDto.setTeamNum(team.getNum()); /* * 중요 **/
		playerRepo.save(playerDto.toEntity());

	}

	@Override
	public void registPlayerWithTeamNum(PlayerDto playerDto) throws Exception {
		playerRepo.save(playerDto.toEntity()); 
	}

	@Override
	public PlayerDto playerInfoByNum(Integer num) throws Exception {
		Player player = playerRepo.findById(num).orElseThrow(()->new Exception("선수 오류"));
		return player.toDto();
	}

	@Override
	public List<PlayerDto> playerInfoByName(String name) throws Exception {
		
		return playerRepo.findByName(name).stream().map((t)->t.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<PlayerDto> playerListInTeamByTeamNum(Integer teamNum) throws Exception {
		Team team = teamRepo.findById(teamNum).orElseThrow(()->new Exception("팀번호 오류"));
		//playerRepo.findById(teamNum).orElseThrow(()->new Exception("팀번호 오류"))
		
		//이미 관계를 맺고 있기 때문에 팀이 가지고 있는 List<Player>를 활용 
		return team.getPlayerList().stream().map((p)->p.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<PlayerDto> playerListInTeamByTeamName(String teamName) throws Exception {
		Team team = teamRepo.findByName(teamName).orElseThrow(()->new Exception("팀이름 오류"));
		return team.getPlayerList().stream().map((p)->p.toDto()).collect(Collectors.toList());
	}

}

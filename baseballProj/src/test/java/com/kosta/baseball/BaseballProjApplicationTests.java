package com.kosta.baseball;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.baseball.dao.BaseballDao;
import com.kosta.baseball.dto.Player;
import com.kosta.baseball.dto.Team;
import com.kosta.baseball.service.BaseballService;

@SpringBootTest
class BaseballProjApplicationTests {

	@Autowired
	private BaseballDao baseballDao;
	
	@Autowired
	private BaseballService baseballService;

	
	@Test
	void contextLoads() {
	}
	@Test
	void insertTeam() throws Exception {
		Team team = Team.builder().name("한화이글스").loc("대전").build();
		baseballDao.registTeam(team);
	}
	
	@Test
	void selectTeamByNum() throws Exception {
		Team team = baseballDao.selectTeamParamNum(1);
		System.out.println(team);
	}
	
	@Test
	void selectTeamByName() throws Exception {
		Team team = baseballDao.selectTeamParamName("KT위즈");
		System.out.println(team);
	}
	
	@Test
	void selectTeamByLoc() throws Exception {
		List<Team> team = baseballDao.selectTeamParamLoc("서울");
		System.out.println(team);
	}
	
	@Test
	void insertPlayer() throws Exception {
		Player player = Player.builder().name("김깅민").backNum(0).teamNum(1).build();
		baseballDao.registPlayer(player);
	}
	
	@Test
	void selectPlayerByNum() throws Exception {
		Player player = baseballDao.selectPlayerByNum(2);
		System.out.println(player);
	}
	
	
	@Test
	void selectPlayerByName() throws Exception {
		List<Player> player = baseballDao.selectPlayerByName("이정후");
		System.out.println(player);
	}
	
	@Test
	void insertPlayerWithTeamName() throws Exception {
		Player player = Player.builder().name("양현종").backNum(54).teamName("KIA타이거즈").build();
		baseballService.registPlayerWithTeamName(player);
	}
	
	@Test
	void insertPlayerWithTeamNum() throws Exception {
		Player player = Player.builder().name("박건우").backNum(37).teamNum(6).build();
		baseballService.registPlayerWithTeamNum(player);
	}

	@Test
	void playerInfoByName() throws Exception {
		List<Player> player = baseballService.playerInfoByName("김강민");
		System.out.println(player);
	}
	
	@Test
	void playerListInTeamByTeamNum() throws Exception {
		List<Player> player = baseballService.playerListInTeamByTeamNum(1);
		System.out.println(player);
	}
	
	@Test
	void playerListInTeamByTeamName() throws Exception {
		List<Player> player = baseballService.playerListInTeamByTeamName("키움히어로즈");	
		System.out.println(player);
	}
	

}

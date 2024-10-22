package com.kosta.baseballdsl.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.baseballdsl.entity.Player;
import com.kosta.baseballdsl.entity.QPlayer;
import com.kosta.baseballdsl.entity.QTeam;
import com.kosta.baseballdsl.entity.Team;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class BaseballRepository {

	@Autowired
	JPAQueryFactory jpaQueryFactory;
	
	@Autowired
	private PlayerRepository playerRepo;
	
	@Autowired
	private TeamRepository teamRepo;

	public void insertTeam(Team team) throws Exception {
		
		teamRepo.save(team);
	}
	
	public Team selectTeamByName(String teamName)throws Exception {
			QTeam team= QTeam.team;
					
			return jpaQueryFactory.select(team)
					.from(team)
					.where(team.name.eq(teamName))
					.fetchOne();
	}
	public Team selectTeamByNum(Integer teamNum) throws Exception {
		QTeam team  = QTeam.team;
		return jpaQueryFactory.select(team)
				.from(team)
				.where(team.num.eq(teamNum))
				.fetchOne();
		
	}
	public List<Team> selectTeamByLoc(String loc) throws Exception {
		QTeam team = QTeam.team;
		return jpaQueryFactory.select(team)
				.from(team)
				.where(team.loc.eq(loc))
				.fetch();
	}
	
	//5,6. 선수 등록(팀 이름으로,번호로)
	public void insertPlayer(Player player) throws Exception {	
		playerRepo.save(player);
	}
	//7.
	public Tuple selectPlayerByNum(Integer num) throws Exception {
		QPlayer player = QPlayer.player;
		QTeam team = QTeam.team;
		
		return jpaQueryFactory.select(player,team.name,team.loc)
				.from(player)
				.leftJoin(team)
				.on(player.teamNum.eq(team.num))
				.where(player.num.eq(num))
				.fetchOne();
	}
	//8.
	public List<Tuple> selectPlayerByName(String name) throws Exception {
		QPlayer player = QPlayer.player;
		QTeam team = QTeam.team;
	
		return  jpaQueryFactory.select(player,team.name,team.loc)
				.from(player)
				.leftJoin(team)
				.on(player.teamNum.eq(team.num))
				.where(player.name.eq(name))
				.fetch();
	}
	//9.
	public List<Tuple> selectPlayerListByTeamNum(Integer teamNum) throws Exception {
		QPlayer player = QPlayer.player;
		QTeam team = QTeam.team;
		
		return jpaQueryFactory.select(player,team.name,team.loc)
				.from(player)
				.leftJoin(team)
				.on(player.teamNum.eq(team.num))
				.where(player.teamNum.eq(teamNum))
				.fetch();
	
	
	}
	
	//10.
	public List<Tuple> slectPlayerListByTeamName(String teamName) throws Exception {
		QPlayer player = QPlayer.player;
		QTeam team = QTeam.team;
		
		return jpaQueryFactory.select(player,team.name,team.loc)
				.from(player)
				.leftJoin(team)
				.on(player.teamNum.eq(team.num))
				.where(team.name.eq(teamName))
				.fetch();

	}
		
}

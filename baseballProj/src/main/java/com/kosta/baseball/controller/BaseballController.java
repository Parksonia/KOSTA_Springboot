package com.kosta.baseball.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.baseball.dto.Player;
import com.kosta.baseball.dto.Team;
import com.kosta.baseball.service.BaseballService;

@RestController // 더이상 view를 주지 않고 return되는건 data 다
public class BaseballController {

		@Autowired
		private BaseballService baseballService;
	  
		//PostMan에서 확인 
		// 1. 팀등록
		@PostMapping("/regTeam")
		public ResponseEntity<String> regTeam(@RequestBody Team team) {
			
			//@RequestBody : json형태의 데이터를받아와서 dto 객체로 바꿔줌
			//(Team team)으로 파라미터를 받을 때는 form-data로 파라미터를 받는다.
			try {
				baseballService.registTeam(team);
				return new ResponseEntity<String>("true",HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>("false",HttpStatus.BAD_REQUEST);
			}
			
		}
	   // 2. 팀 이름으로 팀조회
		@GetMapping("/teamByName")
		public ResponseEntity<Team> teamInfoByName(@RequestParam("teamName")String teamName) {
			try {
				Team team = baseballService.teamInfoByName(teamName);
				return new ResponseEntity<Team>(team,HttpStatus.OK);
				
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Team>(HttpStatus.BAD_REQUEST);
				
			}
		}
	   // 3. 팀 번호로 팀 조회
		@GetMapping("/teamByNum")
		public ResponseEntity<Team> teamInfoByNum(@RequestParam("teamNum")Integer teamNum) {
			
			try {
				Team team = baseballService.teamInfoByNum(teamNum);
				return new ResponseEntity<Team>(team,HttpStatus.OK);
				
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Team>(HttpStatus.BAD_REQUEST);
			}
		}
		
		// 4. 지역으로 팀 조회
	   @GetMapping("/teamByLoc")
	   public ResponseEntity<List<Team>>teamInfoByLoc(@RequestParam("loc")String loc) 
	   {
		   try {
			List<Team>teamList = baseballService.teamListByLoc(loc);
			return new ResponseEntity<List<Team>>(teamList,HttpStatus.OK);
		   } catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Team>>(HttpStatus.BAD_REQUEST);
		   }
	   }
		// 5. 선수 등록(팀이름으로) 
	   @PostMapping("/regPlayerByTeamName")
	   public ResponseEntity<String> regPlayerByTeamName(@RequestBody Player player){
		   try {
			baseballService.registPlayerWithTeamName(player);
			return new ResponseEntity<String>("true",HttpStatus.OK);
		   } catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("true",HttpStatus.BAD_REQUEST);
		}
	  
	   }
	   
	   // 6. 선수 등록(팀번호로) 
	   @PostMapping("/regPlayerByTeamNum")
	   public ResponseEntity<String>regPlayerByTeamNum(@RequestBody Player player){
		try {
			baseballService.registPlayerWithTeamNum(player);
			return new ResponseEntity<String>("true",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}   return new ResponseEntity<String>("false",HttpStatus.BAD_REQUEST);
	   }
	   
	   // 7. 선수 조회(선수번호로, 팀이름포함) 
	   @GetMapping("/playerByNum")
	   public ResponseEntity<Player> playerInfoByNum(@RequestParam("num")Integer num) {
		   try {
			   Player player = baseballService.playerInfoByNum(num);
			   return new ResponseEntity<Player>(player,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
		}
	   }
	  
	   // 8. 선수 조회(선수이름으로, 팀이름포함) 
	   @GetMapping("/playerByName")
	   public ResponseEntity<List<Player>>playerInfoByName(@RequestParam("name")String name){
		   try {
			   List<Player> players = baseballService.playerInfoByName(name);
			   return new ResponseEntity<List<Player>>(players,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Player>>(HttpStatus.BAD_REQUEST);
		}   
	   }
	   
	   // 9. 특정 선수 목록 조회(팀번호로) 
	   @GetMapping("/playerListByTeamNum")
	   	public ResponseEntity<List<Player>>playerListByTeamNum(@RequestParam("teamNum")Integer teamNum) {
		try {
			List<Player>players = baseballService.playerListInTeamByTeamNum(teamNum);
			  return new ResponseEntity<List<Player>>(players,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			  return new ResponseEntity<List<Player>>(HttpStatus.BAD_REQUEST);
		}   
	   }
	   
	   // 10. 특정팀 선수 목록 조회(팀이름으로) 
	   @GetMapping("/playerListByTeamName")
	   public ResponseEntity<List<Player>>playerListByTeamName(@RequestParam("teamName")String teamName) {
		   try {
				List<Player>players = baseballService.playerListInTeamByTeamName(teamName);
				return new ResponseEntity<List<Player>>(players,HttpStatus.OK);
			} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Player>>(HttpStatus.BAD_REQUEST);
		}
	   }
	   
}

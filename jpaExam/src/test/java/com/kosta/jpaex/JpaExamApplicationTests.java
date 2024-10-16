package com.kosta.jpaex;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.jpaex.entity.Actor;
import com.kosta.jpaex.service.ActorService;

@SpringBootTest
class JpaExamApplicationTests {

	@Autowired
	private ActorService actorService;
	
	@Test
	void contextLoads() {
	}

	@Test
	void addActor() throws Exception {
	
		actorService.addActor(Actor.builder().id("minji").name("민지").agency("어도어").birthday(Date.valueOf("2004-05-07")).build());
	}
	@Test
	void findActor() throws Exception{
		Actor actor = actorService.actorInfo("hani");
		System.out.println(actor);
	}
	@Test
	void modifyActor() throws Exception { //id("hani")같은 id를 가지고 add하면 save가 update가 된다.
	
		actorService.addActor(Actor.builder().id("hani").name("하니").agency("하이브").birthday(Date.valueOf("2004-10-06")).build());
	}
	
	@Test
	void deleteActor() throws Exception {
		actorService.removeActor("hani");
	}
	@Test
	void findActorByName() throws Exception{
		Actor actor = actorService.actorInfoByName("하니");
		System.out.println(actor);
	}
	@Test
	void findByAgency() throws Exception {
		List<Actor> list = actorService.actorInfoByAgency("어도어");
		for(Actor a:list) {
			System.out.println(a);	
		}
		
	}	
}

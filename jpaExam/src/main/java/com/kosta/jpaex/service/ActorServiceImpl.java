package com.kosta.jpaex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.jpaex.entity.Actor;
import com.kosta.jpaex.repository.ActorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {
	
	@Autowired
	private ActorRepository actorRepo; //생성자를 자체적으로 가짐
	
	@Override
	public void addActor(Actor actor) throws Exception {
		actorRepo.save(actor);

	}

	@Override
	public Actor actorInfo(String id) throws Exception {
		
		return actorRepo.findById(id).orElseThrow(() ->new Exception("아이디오류"));
	}

	@Override
	public void modifyActor(Actor actor) throws Exception {
	
		actorRepo.findById(actor.getId()).orElseThrow(()->new Exception("아이디오류"));
		actorRepo.save(actor); 
		
	}

	@Override
	public void removeActor(String id) throws Exception {		
		actorRepo.deleteById(id); 		
	}

	@Override
	public Actor actorInfoByName(String name) throws Exception {
		// jpaRepository가 기본적으로 crud 메서드를 가지지만 ,없을 경우에는추가  해준다 
		return actorRepo.findByName(name).orElseThrow(()->new Exception("데이터 없음"));
	}

	@Override
	public List<Actor> actorInfoByAgency(String agency) throws Exception {
		
		return actorRepo.findByAgency(agency);
	}

	
}

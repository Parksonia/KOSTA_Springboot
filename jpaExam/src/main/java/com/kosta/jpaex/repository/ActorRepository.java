package com.kosta.jpaex.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.jpaex.entity.Actor;

public interface ActorRepository extends JpaRepository<Actor,String >{

		Optional<Actor> findByName(String name); //카멜표기법으로 정확하게 적어줘야함  findBy 까지는 정해져 있음
		List<Actor>findByAgency(String agency);
		
}

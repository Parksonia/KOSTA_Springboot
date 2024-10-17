package com.kosta.baseballjpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.baseballjpa.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {// ID=PK값의 타입을 쓰면 됨

	Optional<Team>findByName(String name);

	List<Team> findByLoc(String loc);
}

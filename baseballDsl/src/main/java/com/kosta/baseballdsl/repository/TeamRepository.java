package com.kosta.baseballdsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.baseballdsl.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {

}

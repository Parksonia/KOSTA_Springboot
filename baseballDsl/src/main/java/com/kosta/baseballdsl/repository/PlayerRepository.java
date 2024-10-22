package com.kosta.baseballdsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.baseballdsl.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

}

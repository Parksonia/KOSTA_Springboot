package com.kosta.baseballjpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.baseballjpa.entity.Player;

public interface PlayerRespository extends JpaRepository<Player, Integer> {

	List<Player> findByName(String name);
	Optional<Player> findByTeam_Num(Integer teamNum); // Team = propertyname _ Num : column명
}

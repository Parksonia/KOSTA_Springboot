package com.kosta.boardjpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.boardjpa.entity.Heart;

public interface HeartRepository extends JpaRepository<Heart, Integer> {
	Optional<Heart> findByMember_IdAndBoard_Num(String membId,Integer boardNum);
}

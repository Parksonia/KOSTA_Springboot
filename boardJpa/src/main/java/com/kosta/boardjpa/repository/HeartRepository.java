package com.kosta.boardjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.boardjpa.entity.Heart;

public interface HeartRepository extends JpaRepository<Heart, Integer> {

}

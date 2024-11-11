package com.kosta.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.schedule.entity.Heart;


public interface HeartRepository extends JpaRepository<Heart, Integer> {

}

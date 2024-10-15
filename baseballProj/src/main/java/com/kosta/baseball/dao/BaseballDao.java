package com.kosta.baseball.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.kosta.baseball.dto.Player;

@Mapper
@Repository
public interface BaseballDao {
	void insertPlayer(Player player) throws Exception;
	
	
}

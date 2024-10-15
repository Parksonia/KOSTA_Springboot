package com.kosta.baseball.service;

import org.springframework.stereotype.Service;

import com.kosta.baseball.dao.BaseballDao;
import com.kosta.baseball.dto.Player;
import com.kosta.baseball.dto.Team;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BaseBallServiceImpl implements BaseballService {

	private final BaseballDao baseballDao;
	
	
	@Override
	public void registPlayer(Player player) throws Exception {

	}

}

package com.kosta.boardjpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.boardjpa.service.BoardService;

@SpringBootTest
class BoardJpaApplicationTests {

	@Autowired
	private BoardService boardService;
	
	@Test
	void contextLoads()  throws Exception {
		
	Integer heartBoardNum = boardService.checkHeart("psy", 5);
	System.out.println(heartBoardNum);
	}


	
}

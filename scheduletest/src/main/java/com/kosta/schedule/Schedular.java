package com.kosta.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kosta.schedule.repository.HeartRepository;

import lombok.extern.slf4j.Slf4j;

@Component // 자동 생성됨
@Slf4j // 로깅
public class Schedular {
	
	@Autowired
	private HeartRepository heartRepo;
	
	//@Scheduled(fixedDelay = 3000) //3초마다 실행
	//@Scheduled(initialDelay = 3000, fixedRate = 5000) // 3초 대기 후, 5초마다 실행
	//초(0-59) 분(0-59) 시간(0-24) 일(1-31) 월(1-12) 요일(0:일,1:월,2:화,3:수,4:목,5:금,6:토)
	// (초 분 시간 일 월 요일)
	//@Scheduled(cron ="*/10 * * * * * " ) // 10초 마다(*/10)
	//@Scheduled(cron ="0 0 15 * * * " ) //매일 오후 15시에 실행	
	//@Scheduled(cron ="0 0 14 10,20 * ?" ) //매일 10일,20일 14시에 실행
	//@Scheduled(cron = "0 0 22 L * ?") // 매달 마지막날 22시에 실행
	//@Scheduled(cron="0 0/5 9,18 * * *") //매일9시00분-9:55분, 18시00분-18:55분 사이에 5분 간격으로 실행
	//@Scheduled(cron="0 0/5 9-18 * * *") //매일9시00분-18시55분 사이에 5분 간격으로 실행
	//@Scheduled(cron="0 30 10 1 * *") // 매달 1일 10시 30분에 실행 
	//@Scheduled(cron="0 30 10 ? 3 1-5") //매년 3월내 월-금 10시 30분에 실행
	@Scheduled(cron = "0 22 12 * * *")
	public void run() {
		
		heartRepo.deleteAll();
		log.info("scheduler test");
		
	}
	
}

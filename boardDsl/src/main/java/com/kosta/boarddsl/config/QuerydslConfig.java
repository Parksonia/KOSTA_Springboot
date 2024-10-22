package com.kosta.boarddsl.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
public class QuerydslConfig {

	//@PersistenceContext : EntityManager를 빈으로 주입받기 위한 어노테이션
	@Autowired
	@PersistenceContext
	EntityManager entityManager;
	
	//QueryDSL 을 사용하기 위한 JPAQueryFactory 를 빈으로 등록 
	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
////@Bean :  객체를 만들어 줌 (자동 생성한 객체)
package com.kosta.boarddsl.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

//dsl 관련된 객체를 설정할 클래스
@Configuration
public class QuerydslConfig {

	//@PersistenceContext : (Jpa의)EntityManager를 빈으로 주입받기 위한 어노테이션
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
//@Configuration :여러가지 Bean 객체를 설정할 수 있는 어노테이션

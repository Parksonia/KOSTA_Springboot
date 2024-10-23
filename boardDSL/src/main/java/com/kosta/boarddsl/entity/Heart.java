package com.kosta.boarddsl.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Heart {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	
	private String memId;
	private Integer boardNum;


	
	
}

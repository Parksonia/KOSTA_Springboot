package com.kosta.schedule.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Heart {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	
	private String memId;
	private Integer boardNum;
}

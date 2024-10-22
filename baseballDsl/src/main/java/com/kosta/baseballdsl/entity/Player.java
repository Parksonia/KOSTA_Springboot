package com.kosta.baseballdsl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.kosta.baseballdsl.dto.PlayerDto;

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
public class Player {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	
	@Column
	private String name;

	@Column
	private Integer backNum;
	
	@Column
	private Integer teamNum;
	
//	@Transient
//	@Autowired
//	private Team team;
//
//	
	
	//Entity를 Dto로 바꾸는 메서드 추가 
	public PlayerDto toDto() {
		return PlayerDto.builder().num(num)
				.name(name)
				.backNum(backNum)
				.teamNum(teamNum)
				.build();
	}
		
}

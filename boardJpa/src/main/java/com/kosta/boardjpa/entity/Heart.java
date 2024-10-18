package com.kosta.boardjpa.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="memId")
	private Member member;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="boardNum")
	private Board board;
	

	@Override
	public String toString() {
		return "Heart [num=" + num + ", member=" + member + "]";
	}

	
	
}

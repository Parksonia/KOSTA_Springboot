package com.kosta.baseball.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

	private Integer num;
	private String name;
	private Integer backnum;
	private Integer tramnum;
}

package com.kosta.baseball.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 생성자 생성 후  생성자 파라미터 주입 시(매개변수가 많을 때) 좀 더 명확해 짐
public class Player {

	private Integer num;
	private String name;
	private Integer backNum;
	private Integer teamNum;
	private String teamName;
}

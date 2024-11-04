package com.kosta.board.dto;

import java.sql.Date;

import com.kosta.board.entity.BFile;
import com.kosta.board.entity.Board;
import com.kosta.board.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
	private Integer num;
	private String subject;
	private String content;
	private Integer viewCount;
	private Date createDate;
	private String fileNums;

	
	private String writer; 

	private String nickname;
	private String profileImage;
	
	
	public Board toEntity() {
	Board board =  Board.builder()
						.num(num)
						.subject(subject)
						.content(content)
						.viewCount(viewCount)
						.createDate(createDate)
						.member(Member.builder().id(writer).nickname(nickname).build())
						.build();
	
		return board;
						
						
		
		
		
	}
}

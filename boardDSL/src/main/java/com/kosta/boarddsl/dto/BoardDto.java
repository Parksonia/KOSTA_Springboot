package com.kosta.boarddsl.dto;

import java.sql.Date;

import com.kosta.boarddsl.entity.BFile;
import com.kosta.boarddsl.entity.Board;
import com.kosta.boarddsl.entity.Member;

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
	private Integer imgFileNum;
	private Integer uploadFileNum;
	
	private String writer; 
	private String uploadFileName;
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
		if(imgFileNum !=null) {
			board.setImageFile(BFile.builder().num(imgFileNum).build());
		}
		if(uploadFileNum!=null) {
			board.setUploadFile(BFile.builder().num(uploadFileNum).build());
		}
		return board;
						
						
		
		
		
	}
}

package com.kosta.boardjpa.dto;

import java.sql.Date;

import com.kosta.boardjpa.entity.BFile;
import com.kosta.boardjpa.entity.Board;
import com.kosta.boardjpa.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
	private String uploadFileName;
	private String writer;
	private String nickName;
	private byte[] profileImage;

	
	public Board toEntity() {
		Board board = Board.builder().num(num)
						.subject(subject).content(content).viewCount(viewCount)
						.createDate(createDate)
						.member(Member.builder().id(writer).nickname(nickName).build())
						.build();
		if(imgFileNum !=null) {
			board.setImageFile(BFile.builder().num(imgFileNum).build());
		}
		if(uploadFileNum !=null) {
			board.setUploadFile(BFile.builder().num(uploadFileNum).name(uploadFileName).build());
		}
		
		
				
		return board;
		
	}
}

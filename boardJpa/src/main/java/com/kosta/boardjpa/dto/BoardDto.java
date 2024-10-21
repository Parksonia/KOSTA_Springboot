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
	private String uploadFileName;
	private String writer;
	
	// 실제 board에 노출되야하는 데이터지만 , 테이블에는 없는 값을 dto로 활용한다. 
	private Integer uploadFileNum;
	private String nickname;
	private String profileImage;
	
	public Board toEntity() {
		Board board = Board.builder().num(num)
						.subject(subject).content(content).viewCount(viewCount)
						.createDate(createDate)
						.member(Member.builder().id(writer).nickname(nickname).build())
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

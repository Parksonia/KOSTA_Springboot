package com.kosta.boarddsl.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.kosta.boarddsl.dto.BoardDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//단방향 
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
public class Board {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	
	//private String writer;
	private String subject;
	private String content;
	
	@CreationTimestamp
	private Date createDate;
	
	@ColumnDefault("0")
	private Integer viewCount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="writer")
	private Member member;

	//게시글과 파일은 1:1
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="imgFileNum")
	private BFile imageFile; 
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="uploadFileNum")
	private BFile uploadFile;


	public BoardDto toDto() {
		BoardDto boardDto = BoardDto.builder()
				.num(num)
				.subject(subject)
				.content(content)
				.viewCount(viewCount)
				.createDate(createDate)
				.writer(member.getId())
				.nickname(member.getNickname())
				.build();
	
		if(member.getProfileImage()!=null) {
			try {
				boardDto.setProfileImage(new String(Base64.encodeBase64(member.getProfileImage()),"UTF-8"));
			} catch (Exception e) {
					e.printStackTrace();
			}
			
			if(imageFile !=null) {
				boardDto.setImgFileNum(imageFile.getNum());
			}
		
			if(uploadFile !=null) {
				boardDto.setUploadFileNum(uploadFile.getNum());
				boardDto.setUploadFileName(uploadFile.getName());
				
			}	
		}		
		return null;
	
		
		
		
	}

}

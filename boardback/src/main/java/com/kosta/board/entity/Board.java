package com.kosta.board.entity;

import java.sql.Date;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.relational.core.mapping.Embedded.Nullable;

import com.kosta.board.dto.BoardDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
	
	@ColumnDefault("0") //숫자라고 하더라도 "" 필수 
	private Integer viewCount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="writer")
	private Member member;

	@OneToMany(mappedBy="board" ,fetch=FetchType.LAZY,cascade = CascadeType.REMOVE)
	@Nullable
	private List<BFile> fileList = new ArrayList<>();
	

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
		
		if(fileList !=null && fileList.size()>0) {
			//번호만을 가지고 하나의 문자열을 만들어서(, 구분자로 넣고)데이터를 프론트로 내려 보낼 예정
			boardDto.setFileNums(fileList.stream().map(f->f.getNum()+"").collect(Collectors.joining(",")));
		}
	
		if(member.getProfileImage()!=null) {
			try {
				boardDto.setProfileImage(new String(Base64.encodeBase64(member.getProfileImage()),"UTF-8"));
			} catch (Exception e) {
					e.printStackTrace();
			}
			
		}
				
		return boardDto;
	
	}

}

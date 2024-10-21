package com.kosta.boardjpa.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.kosta.boardjpa.dto.BoardDto;

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
@DynamicInsert //@CreationTimestamp와 함께 사용하여 curdate()가 들어가게함 
@Entity
public class Board {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	private String subject;
	
	@Column(columnDefinition = "LONGTEXT")
	@Lob
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

	
	@Override
	public String toString() {
		return "Board [num=" + num + ", subject=" + subject + ", content=" + content + ", createDate=" + createDate
				+ ", viewCount=" + viewCount + ", member=" + member +", imageFileNum"+imageFile.getNum()+
				", uploadFileNum"+uploadFile.getNum()+"]";
	} 
	
	public BoardDto toDto() {
		BoardDto boardDto = BoardDto.builder()
							.num(num)
							.subject(subject).content(content).createDate(createDate).viewCount(viewCount)
							.writer(member.getId()).nickname(member.getNickname())
							.build();
		
		
		if(member.getProfileImage()!=null) {
			try {
				boardDto.setProfileImage(new String(Base64.encodeBase64(member.getProfileImage()),"UTF-8"));
			} catch (Exception e) {
					e.printStackTrace();
			}
		}
		
		if(imageFile !=null) {
			boardDto.setImgFileNum(imageFile.getNum());
		}
	
		if(uploadFile !=null) {
			boardDto.setUploadFileNum(uploadFile.getNum());
			boardDto.setUploadFileName(uploadFile.getName());
			
		}
	
		return boardDto;
	}
}
//private Integer num;
//private String subject;
//private String content;
//private String writer;
//private String filename;
//private Date create_date;
//private Integer view_cnt;
//private String dfilename;

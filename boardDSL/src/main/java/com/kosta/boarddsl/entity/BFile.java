package com.kosta.boarddsl.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;



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
@DynamicInsert
@Entity
public class BFile {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	
	private String directory;
	private String name;
	private Long size;
	private String contentType;
	
	@CreationTimestamp
	private Date uploadDate;
	
	//Jpa에서 @OneToOne 단방향 안된다.
	@OneToOne(mappedBy = "imageFile",fetch = FetchType.LAZY)
	private Board boardImg;
	
	@OneToOne(mappedBy = "uploadFile",fetch = FetchType.LAZY)
	private Board boardUpload;

	@Override
	public String toString() {
		return "BFile [num=" + num + ", directory=" + directory + ", name=" + name + ", size=" + size + ", contentType="
				+ contentType + ", uploadDate=" + uploadDate + "]";
	}
	
}

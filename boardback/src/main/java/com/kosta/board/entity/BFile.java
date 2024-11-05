package com.kosta.board.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="boardNum")
	private Board board;
	
	@Override
	public String toString() {
		return "BFile [num=" + num + ", directory=" + directory + ", name=" + name + ", size=" + size + ", contentType="
				+ contentType + ", uploadDate=" + uploadDate + "]";
	}
	
}

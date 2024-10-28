package com.kosta.shopdsl.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class Cart {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	
	//카트에 게시글 추가되듯이 한 행씩 데이터로 입력됨
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userid")
	private Member cartUser;
	

	private String gCode;		
	private String gName;
	private Integer gPrice;
	private String gSize;
	private String gColor;
	private Integer gAmount;
	private String gImage;
	
}

//CREATE TABLE IF NOT EXISTS  cart  (
//		   num  int(6) NOT NULL AUTO_INCREMENT,
//		   userid  varchar(10) DEFAULT NULL,
//		   gCode  varchar(20) NOT NULL,
//		   gName  varchar(50) NOT NULL,
//		   gPrice  int(6) NOT NULL,
//		   gSize  char(1) NOT NULL,
//		   gColor  varchar(10) NOT NULL,
//		   gAmount  int(2) NOT NULL,
//		   gImage  varchar(20) NOT NULL,
//		  PRIMARY KEY ( num ),
//		  KEY  cart_userid_fk  ( userid ),
//		  KEY  cart_gCode_fk  ( gCode ),
//		  CONSTRAINT  cart_gCode_fk  FOREIGN KEY ( gCode ) REFERENCES  goods  ( gCode ) ON DELETE CASCADE,
//		  CONSTRAINT  cart_userid_fk  FOREIGN KEY ( userid ) REFERENCES  member  ( userid ) ON DELETE CASCADE
//		) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
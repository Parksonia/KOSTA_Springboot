package com.kosta.shopdsl.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class GOrder {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	private String userid;
	private String gCode;
	private String gName;
	private Integer gPrice;
	private String gSize;
	private String gColor;
	private Integer gAmount;
	private String gImage;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="orderinfoNum")
	private OrderInfo orderinfo;
}

//CREATE TABLE IF NOT EXISTS  gorder  (
//		   num  int(11) NOT NULL AUTO_INCREMENT,
//		   userid  varchar(10) DEFAULT NULL,
//		   gCode  varchar(20) NOT NULL,
//		   gName  varchar(50) NOT NULL,
//		   gPrice  int(6) NOT NULL,
//		   gSize  char(1) NOT NULL,
//		   gColor  varchar(10) NOT NULL,
//		   gAmount  int(2) NOT NULL,
//		   gImage  varchar(20) NOT NULL,
//		   orderinfo_num  int(11) DEFAULT NULL,
//		  PRIMARY KEY ( num ),
//		  KEY  orderinfo_num  ( orderinfo_num ),
//		  CONSTRAINT  gorder_ibfk_1  FOREIGN KEY ( orderinfo_num ) REFERENCES  orderinfo  ( num )
//		) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

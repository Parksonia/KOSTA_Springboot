package com.kosta.shopdsl.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class OrderInfo {


	@Id @GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer num;
	
	@OneToOne(mappedBy = "orderinfoNum")
	private GOrder gorder;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userid")
	private Member member;
	
	private String orderName;
	private String post;
	private String addr1;
	private String addr2;
	private String phone;
	private String payMethod;
	private String orderDay;
}


//CREATE TABLE IF NOT EXISTS  orderinfo  (
//		   num  int(6) NOT NULL AUTO_INCREMENT,
//		   userid  varchar(10) DEFAULT NULL,
//		   orderName  varchar(10) NOT NULL,
//		   post  varchar(5) NOT NULL,
//		   addr1  varchar(500) NOT NULL,
//		   addr2  varchar(500) NOT NULL,
//		   phone  varchar(13) NOT NULL,
//		   payMethod  varchar(20) NOT NULL,
//		   orderDay  date DEFAULT curdate(),
//		  PRIMARY KEY ( num ),
//		  KEY  orderInfo_userid_fk  ( userid ),
//		  CONSTRAINT  orderInfo_userid_fk  FOREIGN KEY ( userid ) REFERENCES  member  ( userid ) ON DELETE CASCADE
//		) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

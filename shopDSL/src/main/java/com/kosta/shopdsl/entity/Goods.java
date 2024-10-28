package com.kosta.shopdsl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Goods {

	@Id
	private String gCode;
	private String gCategory;
	private String gNmae;
	private String gCotent;
	private Integer gPirce;
	private String gImage;
	
	
}

//CREATE TABLE IF NOT EXISTS  goods  (
//		   gCode  varchar(20) NOT NULL,
//		   gCategory  varchar(20) NOT NULL,
//		   gName  varchar(50) NOT NULL,
//		   gContent  varchar(4000) NOT NULL,
//		   gPrice  int(6) NOT NULL,
//		   gImage  varchar(20) NOT NULL,
//		  PRIMARY KEY ( gCode )
//		) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
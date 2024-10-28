package com.kosta.shopdsl.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
public class Member {
	
	@Id
	private String userid;
	
	private String passwd;
	private String username;
	private String post;
	private String addr1;
	private String addr2;
	private String phone1;
	private String phone2;
	private String phone3;
	private String email1;
	private String email2;
	

	
}

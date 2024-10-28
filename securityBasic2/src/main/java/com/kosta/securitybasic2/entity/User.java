package com.kosta.securitybasic2.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@DynamicUpdate
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	// SpringSecurity의 설정 기본이 username,password로 되어 있음
	private String username;
	private String password;
	private String email;
	private String roles;
	@CreationTimestamp
	private Timestamp createDate;



}

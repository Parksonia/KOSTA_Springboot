package com.kosta.bankjpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

	@Id
	private String id;
	@Column
	private String name;

	@Column
	private Integer balance;

	@Column
	private String type;

	@Column
	private String grade;

	
	//Entity에 메서드 작성 가능하다~
	public void deposit(Integer money) throws Exception {
		if(money<=0) throw new Exception("입금액 오류");
		balance += money;
	}
	
	public void withdraw(Integer money) throws Exception {
		if(balance<money) throw new Exception("입금액 오류");
			balance -= money;
	}
	
}

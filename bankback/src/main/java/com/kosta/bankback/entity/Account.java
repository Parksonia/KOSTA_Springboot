package com.kosta.bankback.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.kosta.bankback.dto.AccountDto;

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

//	@Transient : Entity 컬럼에 포함되지 않음
	@Transient
	@Autowired
	ModelMapper modelMapper;

//	public  AccountDto toDto() {
//		return (AccountDto) modelMapper.map(this, AccountDto.class);
//	}
	public void deposit(Integer money) throws Exception {
		if(money < 0) throw new Exception("입금 오류");
		balance += money;
	}
	
	public void withdraw(Integer money) throws Exception {
		if(money<0) throw new Exception("출금액 오류");
		if(balance < money) throw new Exception("잔액 부족");
		balance -= money;
	}
	
	public AccountDto toDto() {
		return AccountDto.builder()
				.id(id)
				.name(name)
				.balance(balance)
				.type(type)
				.grade(grade)
				.build();
	}
	
	
	
	
}


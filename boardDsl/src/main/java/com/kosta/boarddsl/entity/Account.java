package com.kosta.boarddsl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.kosta.boarddsl.dto.AccountDto;

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
	
	@Autowired
	@Transient
	private ModelMapper modelMapper;
	
	
	
	public void deposiot(Integer money) throws Exception {
		if(money<0)throw new Exception("입금오류");
		balance +=money;
	}
	public void withdraw(Integer money) throws Exception {
		if(money<0)throw new Exception("출금오류");
		balance -=money;
	}
	
	public AccountDto toDto() {
		return (AccountDto)modelMapper.map(this, AccountDto.class);
	}
	
	
}

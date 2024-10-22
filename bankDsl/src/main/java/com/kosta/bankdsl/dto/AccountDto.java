package com.kosta.bankdsl.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.kosta.bankdsl.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
	private String id;
	private String name;
	private Integer balance;
	private String type;
	private String grade;

	@Autowired
	private ModelMapper modelMapper;

//	public Account toEntity() {
//		return (Account)modelMapper.map(this, Account.class);
//	}

	public Account toEntity() {
		return Account.builder()
				.id(id)
				.name(name)
				.balance(balance)
				.type(type)
				.grade(grade)
				.build();
				
	}
}

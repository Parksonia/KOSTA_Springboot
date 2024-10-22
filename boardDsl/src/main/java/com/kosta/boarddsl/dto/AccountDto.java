package com.kosta.boarddsl.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.kosta.boarddsl.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

	
	private String id;
	
	private String name;
	
	private Integer balance;
	
	private String type;
	
	private String grade;
	
	@Autowired
	public ModelMapper modelMapper;
	
	public Account toEntity () {
		return(Account) modelMapper.map(this,Account.class);
	}


}

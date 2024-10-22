package com.kosta.boarddsl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.kosta.boarddsl.dto.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member {

	@Id
	private String id;
	
	private String name;
	private String password;
	private String email;
	private String address;
	private String nickname;
	private String profileImage;

	@Transient
	@Autowired
	private ModelMapper modelMapper;


	public MemberDto toDto() {
		return (MemberDto)modelMapper.map(this, MemberDto.class);
	}
}

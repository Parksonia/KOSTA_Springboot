package com.kosta.boarddsl.dto;

import org.modelmapper.ModelMapper;

import com.kosta.boarddsl.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

	private String id;
	
	private String name;
	private String password;
	private String email;
	private String address;
	private String nickname;
	private String profileImage;

	
	private ModelMapper modelMapper;
	public Member toEntity() {
		return (Member)modelMapper.map(this, Member.class);
	}
}

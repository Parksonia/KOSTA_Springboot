package com.kosta.bankback.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.kosta.bankback.dto.MemberDto;

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
	private String id;
	
	@Column
	private String name;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String address;
	@Column
	private String nickname;
	@Column
	private String profileImage; 
	
	
	@Transient
	@Autowired
	private ModelMapper modelMapper;
	
//	public MemberDto toDto() {
//		return (MemberDto)modelMapper.map(this, MemberDto.class);
//	}

	public  MemberDto toDto() {
		return MemberDto.builder()
				.id(id)
				.name(name)
				.password(password)
				.email(email)
				.address(address)
				.nickname(nickname)
				.profileImage(profileImage)
				.profileImageStr(profileImage)
				.build();
		
	}
	
}

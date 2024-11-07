package com.kosta.bankback.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.kosta.bankback.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
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
	private String profileImageStr;
	
	@Autowired
	private ModelMapper modelMapper;
	
//	public Member toEntity() {
//		return(Member)modelMapper.map(this, Member.class);
//	}
	public Member toEntity() {
		return Member.builder()
				.id(id)
				.name(name)
				.nickname(nickname)
				.password(password)
				.email(email)
				.address(address)
				.profileImage(profileImage)
				.build();
			
	}
	
}

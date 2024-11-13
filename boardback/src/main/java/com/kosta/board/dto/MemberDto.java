package com.kosta.board.dto;

import com.kosta.board.entity.Member;

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
	private String username;
	private String password;
	private String email;
	private String roles;
	private String address;
	private String nickname;
	private byte[] profileImage;
	private String profileImageStr;

	
	public Member toEntity() {
		return Member.builder()
				 .id(id)
				 .name(name)
				 .username(username)
				 .roles(roles)
				 .nickname(nickname)
				 .password(password)
				 .email(email)
				 .address(address)
				 .profileImage(profileImage)
				 .build();
	}
	
}

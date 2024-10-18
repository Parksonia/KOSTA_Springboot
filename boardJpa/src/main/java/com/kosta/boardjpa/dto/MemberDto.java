package com.kosta.boardjpa.dto;

import org.modelmapper.ModelMapper;

import com.kosta.boardjpa.entity.Member;

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
	private byte[] profileImage;

	//ModelMapper 활용 dto와 entity가 완전하게 동일할 경우에 사용 가늫하다.
	public Member toEntity() {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(this,Member.class);
	}
	
}

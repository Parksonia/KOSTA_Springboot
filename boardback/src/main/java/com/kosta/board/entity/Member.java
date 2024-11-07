package com.kosta.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.apache.tomcat.util.codec.binary.Base64;

import com.kosta.board.dto.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Member {

	
	@Id
	private String id;
	
	
	private String name;
	private String nickname;
	private String password;
	private String email;
	private String address;
	@Column(columnDefinition = "MEDIUMBLOB")
	@Lob
	private byte[] profileImage;
	
	
	public MemberDto toDto() {
		MemberDto memberDto = MemberDto.builder()
					.id(id)
					.nickname(nickname)
					.password(password)
					.email(email)
					.address(address)
					.profileImage(profileImage)
					.build();
		
			if(profileImage !=null) {
				try {
					memberDto.setProfileImageStr(new String(Base64.encodeBase64(profileImage),"UTF-8"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		return memberDto;
	}
	
}

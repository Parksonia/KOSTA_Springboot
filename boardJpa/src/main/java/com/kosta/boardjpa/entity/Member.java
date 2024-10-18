package com.kosta.boardjpa.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.modelmapper.ModelMapper;

import com.kosta.boardjpa.dto.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
	
	@Column(columnDefinition = "MEDIUMBLOB")
	@Lob
	private byte[] profileImage;
	
	
	@OneToMany(mappedBy="member",fetch = FetchType.LAZY)
	private List<Board> board = new ArrayList<Board>();
	
	
	//좋아요 한 목록 같은걸 보는데 활용할 수 있다. 
	@OneToMany(mappedBy="member",fetch = FetchType.LAZY)
	private List<Heart> heart = new ArrayList<>();


	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", address="
				+ address + ", nickname=" + nickname + "]";
	}
	
	public MemberDto toDto() {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(this,MemberDto.class);
	}
	

}

//private String id;
//private String name;
//private String password;
//private String email;
//private String address;
//private String nickname;
//private String profile_image;
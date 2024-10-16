package com.kosta.jpaex.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Actor {

	@Id
	private String id;
	
	//@Column(name="nm") 만약 entity와 db의 컬럼명과 다르다면 이렇게 이름을 알려줘야 함
	@Column
	private String name;
	
	@Column
	private Date birthday;
	
	@Column
	private String agency;
	
}

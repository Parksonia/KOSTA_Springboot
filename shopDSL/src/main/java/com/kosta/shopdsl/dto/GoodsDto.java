package com.kosta.shopdsl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoodsDto {

	private String gCode;
	private String gCategory;
	private String gName;
	private String gContent;
	private Integer gPrice;
	private String gImage;
	
}

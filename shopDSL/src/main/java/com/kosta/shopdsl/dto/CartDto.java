package com.kosta.shopdsl.dto;

import com.kosta.shopdsl.entity.Cart;
import com.kosta.shopdsl.entity.Goods;
import com.kosta.shopdsl.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {

	private Integer num;
	private String userid;
	private String gCode;
	private String gName;
	private Integer gPrice;
	private String gSize;
	private String gColor;
	private Integer gAmount;
	private String gImage;
	

	private Cart toEntity() {
		return Cart.builder()
				.num(num)
				.gName(gName)
				.gPrice(gPrice)
				.gSize(gSize)
				.gColor(gColor)
				.gAmount(gAmount)
				.gImage(gImage)
				.goodsCode(Goods.builder().gCode(gCode).build())
				.cartUser(Member.builder().userid(userid).build())
				.build();
		
	}
}

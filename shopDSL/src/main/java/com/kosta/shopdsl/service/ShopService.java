package com.kosta.shopdsl.service;

import java.util.List;

import com.kosta.shopdsl.entity.Cart;
import com.kosta.shopdsl.entity.GOrder;
import com.kosta.shopdsl.entity.Goods;
import com.kosta.shopdsl.entity.OrderInfo;

public interface ShopService {

	List<Goods> goodsLists () throws Exception;
	Goods goodsDetail (String gCode) throws Exception;
	boolean addCartList(Cart cart) throws Exception;
	List<Cart> allCartList(String userid) throws Exception;
	void  deleteMultiItemCart(List<Integer>list) throws Exception;
	void  deleteOneItemCart(Integer num) throws Exception;
	void  cartUpdateAmount(Integer num,Integer gAmount) throws Exception;
	Cart confirmCartOrderList(Integer num) throws Exception;
	List<Cart> confirmCartOrderListAll(List<Integer>list) throws Exception;
	
	//주문완료
	void orderDone(OrderInfo orderInfo,GOrder gOrder,Integer orderNum) throws Exception;
	List<GOrder>  orderAllDone(OrderInfo orderInfo,List<Integer>nums) throws Exception;
	
}

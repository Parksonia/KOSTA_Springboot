package com.kosta.shopdsl.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.shopdsl.entity.Cart;
import com.kosta.shopdsl.entity.GOrder;
import com.kosta.shopdsl.entity.Goods;
import com.kosta.shopdsl.entity.Member;
import com.kosta.shopdsl.entity.OrderInfo;
import com.kosta.shopdsl.service.ShopService;

@Controller
public class ShopController {
	
	@Autowired
	private HttpSession session;

	@Autowired
	private ShopService shopService;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value="/main")
	public ModelAndView shopMain() {
		ModelAndView mav = new ModelAndView();
		
		try {
			List<Goods> lists = shopService.goodsLists();
			mav.addObject("goods", lists);
			mav.addObject("user", session.getAttribute("member"));
			mav.setViewName("shopMain");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		return mav;
	}

	
	@RequestMapping(value="/cartList") 
	public ModelAndView cartList() {
		ModelAndView mav = new ModelAndView();
		Member member = (Member)session.getAttribute("member");
		session.setAttribute("user", member);
		
		try {
			List<Cart> cartlist  = shopService.allCartList(member.getUserid());
			mav.addObject("cartList", cartlist);
			mav.setViewName("cartList");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "장바구니조회");
		}
		
		return mav;
	}
	

	
	@RequestMapping(value="goodsRetrieve")
	public ModelAndView goodsDetail(@RequestParam("gCode")String gCode,
				@RequestParam(name="cart",required = false,defaultValue ="N")String cart) {
		ModelAndView mav = new ModelAndView();
		System.out.println(gCode);
		
		try {
			
			Goods item = shopService.goodsDetail(gCode);
			mav.addObject("item", item);
			mav.addObject("cart",cart);
			
			mav.setViewName("goodsRetrieve");
			Member member = (Member)session.getAttribute("member");
			session.setAttribute("user", member);
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action","상품 상세 조회" );
			mav.addObject("message", "상품 상세 조회 실패");
			mav.setViewName("memberResult");
		}
		return mav;
	}
	
//	@RequestMapping(value="addCart",method=RequestMethod.GET)
//	public ModelAndView addCart(@ModelAttribute Cart cart,Model model) {
//		
//		ModelAndView mav = new ModelAndView();
//		String userid = ((Member)session.getAttribute("member")).getUserid();
//		cart.setUserid(userid);
//	
//		try {
//			boolean addCart  = shopService.addCartList(cart);
//			Cart cartOne = new Cart();
//			mav.setViewName("redirect:/goodsRetrieve?gCode="+cart.getgCode()+"&cart=Y");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			mav.addObject("action","장바구니 추가" );
//			mav.addObject("message", "장바구니 추가 실패");
//			mav.setViewName("memberResult");
//		}
//		return mav;
//	}
//	
	@RequestMapping(value="orderConfirm")
	public ModelAndView orderConfirm(Goods goods,@RequestParam("gSize")String gSize,@RequestParam("gColor")String gColor,
							@RequestParam("gAmount")Integer gAmount) {
		//gDTO : 상품 정보 
		//gSize..등 
		
		ModelAndView mav = new ModelAndView();
		try {
			mav.addObject("gDTO", goods);
			mav.addObject("gSize", gSize);
			mav.addObject("gColor", gColor);
			mav.addObject("gAmount", gAmount);
			mav.setViewName("orderConfirm");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="CartDelAll")
	public String cartDeleteAll (@RequestParam("check")Integer num[]) {
		
		try {
			shopService.deleteMultiItemCart(Arrays.asList(num));
		} catch (Exception e) {
		 e.printStackTrace();
		}
		
		return "redirect:/cartList";
				
	}
	@RequestMapping("/cartDelete")
	@ResponseBody
	public void cartDeleteOne(@RequestParam("num")Integer num) {
	
	try {
		shopService.deleteOneItemCart(num);
	} catch (Exception e) {
		e.printStackTrace();
	}	
		
	}
	@RequestMapping("cartUpdate")
	@ResponseBody
	public void  updateGAmount(@RequestParam("num")Integer num,@RequestParam("gAmount")Integer gAmount) {
	
		try {
			shopService.cartUpdateAmount(num, gAmount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("cartOrderConfirm")
	public ModelAndView cartOrderConfirm(@RequestParam("num")Integer num) {
		ModelAndView mav = new ModelAndView();
			
			try {
			mav.addObject("cDTO", shopService.confirmCartOrderList(num));
			mav.setViewName("orderConfirm");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action","장바구니 주문 확인" );
			mav.addObject("message", "장바구니 주문 확인 실패");
			mav.setViewName("memberResult");
		}
		return mav;
	}
	//multiple 주문
	@RequestMapping("cartOrderAllConfirm")
	public ModelAndView cartOrderAllConfirm(@RequestParam("check") Integer num[] ) {
		ModelAndView mav = new ModelAndView ();
		try {
			List<Cart> cartList  = shopService.confirmCartOrderListAll(Arrays.asList(num));
			mav.addObject("cartList", cartList);
			mav.setViewName("orderAllConfirm");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action","장바구니 주문 확인" );
			mav.addObject("message", "장바구니 주문 확인 실패");
			mav.setViewName("memberResult");
		}
		
		return mav;
		
	}
	
//	@RequestMapping("/cartOrderDone")
//	public ModelAndView cartOrderDone(@ModelAttribute OrderInfo orderInfo,@ModelAttribute GOrder gOrder,
//							@RequestParam(name="orderNum",required = false)Integer orderNum) {
//							//required = false 를 하지 않으면 default가 true이기 때문에 만약에 넘어오지않으면 404에러가 된다. 
//							// cart에서 주문하는 경우에만 카트에서 삭제하기 위해 orderNum이 필요 
//		ModelAndView mav = new ModelAndView();
//		
//		try {
//		Member member= (Member)session.getAttribute("member");	
//		orderInfo.setUserid(member.getUserid());
//		gOrder.setUserid(member.getUserid());
//		
//		shopService.orderDone(orderInfo, gOrder, orderNum);
//		//mav.addObject("orderInfo", orderInfo);
//		//mav.addObject("order", gOrder);   @ModelAttribute사용하면 파라미터로 받은것을 그대로 내려보낼때 생략 해줄 수 있다.
//		mav.setViewName("orderDone");
//	
//		} catch (Exception e) {
//			e.printStackTrace();
//			mav.addObject("action","상품결제" );
//			mav.addObject("message", "상품결제실패");
//			mav.setViewName("memberResult");
//		}
//		
//		
//		return mav;
//		
//	}
//	//cart-multiple 주문 완료
//	@RequestMapping("/cartOrderAllDone")
//	public ModelAndView cartOrderAllDone(@RequestParam("num")Integer nums[],@ModelAttribute OrderInfo orderInfo) {
//			ModelAndView mav = new ModelAndView();
//		try {
//			Member member= (Member)session.getAttribute("member");	
//			orderInfo.setUserid(member.getUserid());
//
//			List<GOrder> gOrderList = shopService.orderAllDone(orderInfo, Arrays.asList(nums));
//			mav.addObject("orderAllDone", gOrderList);
//			mav.setViewName("orderAllDone");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			mav.addObject("action","상품결제" );
//			mav.addObject("message", "상품결제실패");
//			mav.setViewName("memberResult");
//		}
//		
//		return mav;
//	}
	
}

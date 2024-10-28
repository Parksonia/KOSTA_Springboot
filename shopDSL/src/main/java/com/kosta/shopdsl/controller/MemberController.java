package com.kosta.shopdsl.controller;

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

import com.kosta.shopdsl.entity.Member;
import com.kosta.shopdsl.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private MemberService memberService;

	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam("userid")String userid,
			@RequestParam("passwd")String passwd, Model model) {
		
		try {
			Member member = memberService.login(userid, passwd);
			model.addAttribute("user", member);
			session.setAttribute("member", member); // 그냥 session "user", member해도 됨 
			return"redirect:/main"; // main url로 이동 
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("action","로그인"); 
			model.addAttribute("message", e.getMessage());
			return "memberResult";
		}
		
		
	}
	
	@RequestMapping(value="/logout") 
	public String logout() {
		Member member = (Member)session.getAttribute("member");
		if(member !=null) session.invalidate();
		return"index";
	}
	
	@RequestMapping(value="/signUp" ,method=RequestMethod.GET)
	public String signUp() {
		return "signUpForm";
	}

	@RequestMapping(value="/signUp" ,method=RequestMethod.POST)
	public ModelAndView signUp(@ModelAttribute Member member) {
	
			ModelAndView mav = new ModelAndView();
		try {
			memberService.signUp(member);
			mav.addObject("message","회원가입성공");
			mav.setViewName("index");
			
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("message", "회원가입실패");
			mav.setViewName("signUpForm");
		}
		
		
		return mav;
	}

	@RequestMapping(value="idCheck",method=RequestMethod.GET,produces = "application/text; charset=UTF-8;")
	@ResponseBody
	public String idDoubleCheck(@RequestParam("id")String userid,Model model) {
		
		String result="사용가능";
		try {
			boolean check = memberService.checkDoubleId(userid); //true면 이미 존재하는 아이디 
			
			  if(check) {
				 result = "사용불가" ; 
				 return String.valueOf(result); 
			  }else { 
				  return String.valueOf(result); 
				  }
				
		} catch (Exception e) {
			e.printStackTrace();
			return  e.getMessage();
		}

	}
	
	@RequestMapping(value="/mypage") 
	public String myPage(Model model) {
	
		Member member =(Member)session.getAttribute("member");
		model.addAttribute("user", member);
		
		return "mypage";
	}
	@RequestMapping(value="/updateMember", method=RequestMethod.POST)
	public String  updateMember(@ModelAttribute Member member,Model model) {
		
		try {
			memberService.modifyMyPage(member);
			//로그인 한 회원의 정보를 수정했으니 세션에 저장된 정보도 삭제 후 다시 저장해줘야함 **
			session.removeAttribute("member");
			session.setAttribute("member", memberService.myPage(member.getUserid()));
			model.addAttribute("message","회원정보수정 성공");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "회원정보수정 실패");
		}
		return "memberResult";
		
	}
	
}

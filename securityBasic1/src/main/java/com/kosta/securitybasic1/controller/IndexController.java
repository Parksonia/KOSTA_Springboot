package com.kosta.securitybasic1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@GetMapping({"","/"})
	@ResponseBody
	public String index() {
		return "인덱스입니다.";
	}
}

package com.mysite.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="api/gb")
public class ApiGuestBookController {
	
	//원래처럼 .jsp를 찾지말고 보낸 JSON의 body에 리턴값을 넣어서 보내라는 것
	@ResponseBody
	@RequestMapping(value="list", method=RequestMethod.POST)
	public String list() {
		System.out.println("ajax-list");
		String str = "okay";
		
		return str;
	}
}

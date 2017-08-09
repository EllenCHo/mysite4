package com.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysite.service.UserService;
import com.mysite.vo.UserVo;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/joinform", method = RequestMethod.GET)
	public String joinform() {
		return "user/joinform";
	}
	
	@RequestMapping(value="/user/join", method = RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println(userVo.toString());
		
		userService.join(userVo);
		return "user/joinsuccess";
	}
}

package com.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.service.UserService;
import com.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/joinform", method = RequestMethod.GET)
	public String joinform() {
		return "user/joinform";
	}
	
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println(userVo.toString());
		
		userService.join(userVo);
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/loginform", method=RequestMethod.GET)
	public String loginform() {
		return "user/loginform";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
		UserVo authUser = userService.getUser(email, password);
		if(authUser != null) {
			session.setAttribute("authUser", authUser);
			return "redirect:/main";
		} else {
			return "redirect:/user/loginform?result=fail";
		}
	}
}

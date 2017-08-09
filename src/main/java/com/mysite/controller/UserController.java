package com.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	@RequestMapping(value="/user/joinform", method = RequestMethod.GET)
	public String joinform() {
		return "user/joinform";
	}
}

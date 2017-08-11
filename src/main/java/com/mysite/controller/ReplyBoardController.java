package com.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/replyboard")
public class ReplyBoardController {
	@RequestMapping("/list")
	public String list() {
		return "/replyboard/list";
	}
}

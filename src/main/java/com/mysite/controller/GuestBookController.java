package com.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysite.service.GuestBookService;
import com.mysite.vo.GuestBookVo;

@Controller
@RequestMapping(value ="gb")
public class GuestBookController {
	@Autowired
	GuestBookService guestBookService;
	
	@RequestMapping(value ="/list")
	public String list(Model model) {
		List<GuestBookVo> list = guestBookService.getList();
		model.addAttribute("list", list);
		return "/guestbook/list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute GuestBookVo guestBookVo) {
		guestBookService.insert(guestBookVo);
		return "redirect:/gb/list";
	}

}

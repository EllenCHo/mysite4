package com.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.service.GuestBookService;
import com.mysite.vo.GuestBookVo;

@Controller
@RequestMapping(value ="gb")
public class GuestBookController {
	@Autowired
	GuestBookService guestBookService;
	
	@RequestMapping(value="/list2", method=RequestMethod.GET)
	public String listAjax(Model model) {
		return "/guestbook/list-ajax";
	}
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
	
	@RequestMapping(value="/deleteform")
	public String deleteform(@RequestParam("no") int no, Model model) {
		model.addAttribute("no", no);
		return "/guestbook/deleteform";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@RequestParam("no") int no, @RequestParam("password") String password) {
		guestBookService.delete(no, password);
		return "redirect:/gb/list";
	}

}

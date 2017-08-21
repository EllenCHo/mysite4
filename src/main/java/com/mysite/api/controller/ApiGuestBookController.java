package com.mysite.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.service.GuestBookService;
import com.mysite.vo.GuestBookVo;

@Controller
@RequestMapping(value="api/gb")
public class ApiGuestBookController {
	@Autowired
	private GuestBookService guestBookService;
	
	//원래처럼 .jsp를 찾지말고 보낸 JSON의 body에 리턴값을 넣어서 보내라는 것
	@ResponseBody
	@RequestMapping(value="list", method=RequestMethod.POST)
	public List<GuestBookVo> list() {
		List<GuestBookVo> list = guestBookService.getList();
		
		return list;
	}
	
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public void add(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println(guestBookVo.toString());
		guestBookService.insert(guestBookVo);
	}
}

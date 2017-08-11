package com.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.service.ReplyBoardService;
import com.mysite.vo.ReplyBoardVo;

@Controller
@RequestMapping("/replyboard")
public class ReplyBoardController {
	@Autowired
	ReplyBoardService replyBoardService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<ReplyBoardVo> list = replyBoardService.getList();
		
		model.addAttribute("list",list);
		
		return "/replyboard/list";
	}
	
	@RequestMapping("/writeform")
	public String writeform() {
		return "/replyboard/writeform";
	}
	
	@RequestMapping("/write")
	public String write(@ModelAttribute ReplyBoardVo vo) {
		replyBoardService.insert(vo);
		
		return "redirect:/replyboard/list";
	}
	
	@RequestMapping("/read")
	public String read(@RequestParam("no") int no, Model model) {
		ReplyBoardVo vo = replyBoardService.read(no);
		
		model.addAttribute("vo", vo);
		
		return "/replyboard/read";
	}
}

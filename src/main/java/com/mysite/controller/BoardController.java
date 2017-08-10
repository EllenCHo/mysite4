package com.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.service.BoardService;
import com.mysite.vo.BoardVo;
import com.mysite.vo.PageVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value ="/list")
	public String list(@RequestParam("currNo") int currNo, Model model) {
		PageVo page = boardService.getPage(currNo);
		List<BoardVo> list = boardService.getList(currNo, page.getPageNo());
		
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "/board/list";
	}

}

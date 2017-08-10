package com.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping("/writeform")
	public String writeform(@RequestParam("currNo") int currNo, Model model) {
		model.addAttribute("currNo", currNo);
		return "/board/writeform";
	}

	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String write(@ModelAttribute BoardVo boardVo) {
		boardService.insert(boardVo);
		return "redirect:/board/list?currNo=1";		//이따가 읽기가 되면 쓴 게시글을 볼수있도록 이동
	}
	
	@RequestMapping(value="/read")
	public String read(@RequestParam("no") int no, @RequestParam("currNo") int currNo, Model model) {
		BoardVo boardVo = boardService.read(no);
		
		model.addAttribute("vo", boardVo);
		model.addAttribute("currNo", currNo);
		return "/board/read";
	}
	
	@RequestMapping(value="/modifyform")
	public String modifyform(@RequestParam("currNo") int currNo, @RequestParam("boardNo") int boardNo, Model model) {
		BoardVo boardVo = boardService.read(boardNo);
		String temp = boardVo.getContent().replace("<br/>", "\r\n");
		boardVo.setContent(temp);
		
		model.addAttribute("vo", boardVo);
		model.addAttribute("currNo", currNo);
		return "/board/modifyform";
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String modify(@RequestParam("currNo") int currNo, @RequestParam("boardNo") int boardNo, 
						 @RequestParam("title") String title, @RequestParam("content") String content, Model model) {
		boardService.update(boardNo, title, content);
		
		return "redirect:/board/read?currNo="+currNo+"&no="+boardNo;
	}
}

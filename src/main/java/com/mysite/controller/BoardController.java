package com.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		model.addAttribute("act", "list");
		return "/board/list";
	}
	
	@RequestMapping("/writeform")
	public String writeform(@RequestParam("currNo") int currNo, Model model) {
		model.addAttribute("currNo", currNo);
		return "/board/writeform";
	}

	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String write(@ModelAttribute BoardVo boardVo) {
		int no = boardService.insert(boardVo);
		return "redirect:/board/list/read/w?currNo=1&no="+no+"&kwd=";		
	}
	
	@RequestMapping(value="/{act}/read/{user}")
	public String read(@PathVariable("act") String act , @PathVariable("user") String user, 
					   @RequestParam("no") int no, @RequestParam("currNo") int currNo,
					   @RequestParam("kwd") String voca, Model model) {
		BoardVo boardVo = boardService.read(no, user);
		
		model.addAttribute("vo", boardVo);
		model.addAttribute("act", act);
		model.addAttribute("voca", voca);
		model.addAttribute("currNo", currNo);
		return "/board/read";
	}
	
	@RequestMapping(value="/modifyform")
	public String modifyform(@RequestParam("currNo") int currNo, @RequestParam("boardNo") int boardNo, Model model) {
		BoardVo boardVo = boardService.read(boardNo, "");
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
		
		return "redirect:/board/list/read/m?currNo="+currNo+"&no="+boardNo+"&kwd=";
	}
	
	@RequestMapping("/{act}/delete")
	public String delete(@RequestParam("currNo") int currNo, @PathVariable("act") String act,
						 @RequestParam("boardNo") int boardNo, @RequestParam("auth") int auth, 
						 @RequestParam("user") int user, @RequestParam("kwd") String voca) {
		if(auth == user) {
			boardService.delete(boardNo);
		}
		return "redirect:/board/"+act+"?currNo="+currNo+"&kwd="+voca;
	}
	
	@RequestMapping(value="search", method=RequestMethod.GET)
	public String search(@RequestParam("currNo") int currNo, @RequestParam("kwd") String voca, Model model) {
		PageVo page = boardService.getSearchPage(currNo, voca);
		List<BoardVo> list = boardService.search(voca, currNo, page.getPageNo());
		
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("act", "search");
		model.addAttribute("voca", voca);
		return "/board/list";
	}
}

package com.mysite.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.BoardDao;
import com.mysite.vo.BoardVo;
import com.mysite.vo.PageVo;

@Service
public class BoardService {
	@Autowired
	BoardDao boardDao;
	@Autowired
	PageVo pageVo;
	
	public List<BoardVo> getList(int currNo, int pageNo) {
		int totalCount = boardDao.getTotalCount();
		return boardDao.getList(currNo, pageNo, totalCount);
	}
	
	public PageVo getPage(int currNo) {
		int totalCount = boardDao.getTotalCount();
		pageVo.PageSetting(currNo, totalCount);
		return pageVo;
	}
	
	public int insert(BoardVo boardVo) {
		String content = boardVo.getContent().replace("\r\n", "<br/>");
		boardVo.setContent(content);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(cal.getTime());
		
		boardVo.setRegDate(date);
		
		return boardDao.insert(boardVo);
	}
	
	public BoardVo read(int no, String user) {
		BoardVo vo =  boardDao.read(no);
		
		if(user.equals("u") && vo != null) {
			boardDao.hit(no);
		}
		
		return vo;
	}
	
	public int update(int boardNo, String title, String content) {
		String temp = content.replace("\r\n", "<br/>");
		
		return boardDao.update(boardNo, title, temp);
	}
	
	public int delete(int boardNo) {
		return boardDao.delete(boardNo);
	}
	
	public List<BoardVo> search(String voca, int currNo, int pageNo) {
		int totalCount = boardDao.getTotalSearchCount(voca);
		return boardDao.search(voca, currNo, pageNo, totalCount);
	}
	
	public PageVo getSearchPage(int currNo, String voca) {
		int totalCount = boardDao.getTotalSearchCount(voca);
		pageVo.PageSetting(currNo, totalCount);
		return pageVo;
	}

}

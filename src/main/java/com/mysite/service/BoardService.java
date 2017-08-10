package com.mysite.service;

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
	
	public PageVo getPage(int pageNo) {
		int totalCount = boardDao.getTotalCount();
		pageVo.PageSetting(pageNo, totalCount);
		return pageVo;
	}
}

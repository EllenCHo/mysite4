package com.mysite.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.ReplyBoardDao;
import com.mysite.vo.ReplyBoardVo;

@Service
public class ReplyBoardService {
	@Autowired
	ReplyBoardDao replyBoardDao;
	
	public int insert(ReplyBoardVo vo) {
		String content = vo.getContent().replace("\r\n", "<br/>");
		vo.setContent(content);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(cal.getTime());
		
		vo.setRegDate(date);
		
		return replyBoardDao.insert(vo);
	}

}

package com.mysite.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.ReplyBoardDao;
import com.mysite.vo.ReplyBoardVo;

@Service
public class ReplyBoardService {
	@Autowired
	ReplyBoardDao replyBoardDao;
	
	public List<ReplyBoardVo> getList() {
		return replyBoardDao.getList();
	}
	
	public int insert(ReplyBoardVo vo) {
		String content = vo.getContent().replace("\r\n", "<br/>");
		vo.setContent(content);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(cal.getTime());
		
		vo.setRegDate(date);
		
		if(vo.getGroupNo() != 0) {			//답글을 작성할때
			replyBoardDao.increseOrderNo(vo.getGroupNo(), vo.getOrderNo());
			vo.setOrderNo(vo.getOrderNo()+1);
			vo.setDepth(vo.getDepth()+1);
		} 
		return replyBoardDao.insert(vo);
	}
	
	public ReplyBoardVo read(int no) {
		return replyBoardDao.read(no);
	}

}

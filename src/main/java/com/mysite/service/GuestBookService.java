package com.mysite.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.GuestBookDao;
import com.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {
	@Autowired
	GuestBookDao guestBookDao;
	
	public List<GuestBookVo> getList() {
		return guestBookDao.getList();
	}
	
	public int insert(GuestBookVo guestBookVo) {
		String content = guestBookVo.getContent().replace("\r\n", "<br/>");
		guestBookVo.setContent(content);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(cal.getTime());
		
		guestBookVo.setRegDate(date);
		
		return guestBookDao.insert(guestBookVo);
	}
	
	public int delete(int no, String password) {
		return guestBookDao.delete(no, password);
	}
	
	public GuestBookVo writeVo(GuestBookVo guestBookVo) {
		String content = guestBookVo.getContent().replace("\r\n", "<br/>");
		guestBookVo.setContent(content);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(cal.getTime());
		
		guestBookVo.setRegDate(date);
		
		int no = guestBookDao.insertVo(guestBookVo);
		
		return guestBookDao.selectByNo(no);
	}
}

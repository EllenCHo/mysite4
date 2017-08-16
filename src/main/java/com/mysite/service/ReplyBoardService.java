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
			int i = replyBoardDao.increseOrderNo(vo.getGroupNo(), vo.getOrderNo(), vo.getDepth());
			if(i==0) {						//같은 depth 계층에서 답글을 쓸
				Integer no = replyBoardDao.getMaxOrderNo(vo.getGroupNo(), vo.getDepth()+1);
				int orderNo = no == null? 1 : no;			//처음 답글을 쓰는거라서 null이 들어오면 1로 치환
				
				vo.setOrderNo(orderNo+1);
			}else {
				Integer no = replyBoardDao.getMaxOrderNo(vo.getGroupNo(), vo.getDepth()+1);
				int orderNo = no == null? vo.getOrderNo() : no;			//처음 답글을 쓰는거라서 null이 들어오면 그 전의 depth의 orderNo로 치환
				vo.setOrderNo(orderNo+1);
			}
			vo.setDepth(vo.getDepth()+1);
		} 
		return replyBoardDao.insert(vo);
	}
	
	public ReplyBoardVo read(int no, String user) {
		ReplyBoardVo vo = replyBoardDao.read(no);
		if(user.equals("u") && vo != null) {
			replyBoardDao.hit(no);
		}
		return vo;
	}
	
	public int delete(ReplyBoardVo vo) {
		int min = replyBoardDao.selectMinNo(vo);
		return replyBoardDao.delete(vo, min);
	}
	
	public int update(int no, String title, String content) {
		content.replace("\r\n", "<br/>");
		return replyBoardDao.update(no, title, content);
	}
	
	public List<ReplyBoardVo> search(String kwd){
		String voca = '%'+kwd+'%';
		return replyBoardDao.search(voca);
	}

}

package com.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	@Autowired
	SqlSession sqlSession;
	
	public List<GuestBookVo> getList() {
		return sqlSession.selectList("guestbook.getList");
	}
	
	public int insert(GuestBookVo guestBookVo) {
		return sqlSession.insert("guestbook.insert", guestBookVo);
	}
}

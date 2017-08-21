package com.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public int delete(int no, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("password", password);
		
		return sqlSession.delete("guestbook.delete", map);
	}
	
	public int insertVo(GuestBookVo guestBookVo) {
		sqlSession.insert("guestbook.insertVo", guestBookVo);
		return guestBookVo.getNo();
	}
	
	public GuestBookVo selectByNo(int no) {
		return sqlSession.selectOne("guestbook.selectByNo", no);	}
}

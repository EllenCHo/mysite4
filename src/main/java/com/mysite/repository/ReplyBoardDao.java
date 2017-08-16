package com.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.ReplyBoardVo;

@Repository
public class ReplyBoardDao {
	@Autowired
	SqlSession sqlSession;
	
	public List<ReplyBoardVo> getList() {
		return sqlSession.selectList("replyboard.getList");
	}
	
	public int insert(ReplyBoardVo vo) {
		return sqlSession.insert("replyboard.insert", vo);
	}
	
	public ReplyBoardVo read(int no) {
		return sqlSession.selectOne("replyboard.read", no);
	}
	
	public int increseOrderNo(int groupNo, int orderNo, int depth) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo);
		map.put("depth", depth);
		return sqlSession.update("replyboard.increse", map);
	}
	
	public Integer getMaxOrderNo(int groupNo, int depth) {
		Map<String, Object> map = new HashMap<String, Object> ();
		map.put("groupNo", groupNo);
		map.put("depth", depth);
		return sqlSession.selectOne("replyboard.getMaxOrderNo", map);
	}
	
	public int delete(ReplyBoardVo vo, int min) {
		Map<String, Object> map = new HashMap<String, Object> ();
		map.put("vo", vo);
		map.put("min", min);
		return sqlSession.delete("replyboard.delete", map);
	}
	
	public int selectMinNo(ReplyBoardVo vo) {
		return sqlSession.selectOne("replyboard.selectMinNo", vo);
	}
	
	public int update(int no, String title, String content) {
		Map<String, Object> map = new HashMap<String, Object> ();
		map.put("no", no);
		map.put("title", title);
		map.put("content", content);
		return sqlSession.update("replyboard.update", map);
	}
	
	public int hit(int no) {
		return sqlSession.update("replyboard.hit", no);
	}
	
	public List<ReplyBoardVo> search(String kwd) {
		return sqlSession.selectList("replyboard.search", kwd);
	}

}

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
	
	public int increseOrderNo(int groupNo, int orderNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo);
		return sqlSession.update("replyboard.increse", map);
	}

}

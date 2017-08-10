package com.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	SqlSession sqlSession;
	
	public List<BoardVo> getList(int currNo, int pageNo, int totalCount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("endNo", totalCount - (currNo-1) * pageNo);
		map.put("startNo", totalCount - currNo * pageNo);
		return sqlSession.selectList("board.getList", map);
	}
	
	public int getTotalCount() {
		return sqlSession.selectOne("board.getTotalCount");
	}
	
	public int insert(BoardVo boardVo) {
		sqlSession.insert("board.insert", boardVo);
		return sqlSession.selectOne("board.boardNo");
	}
	
	public BoardVo read(int no) {
		return sqlSession.selectOne("board.read", no);
	}
	
	public int update(int boardNo, String title, String content) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", boardNo);
		map.put("title", title);
		map.put("content", content);
		
		return sqlSession.update("board.update", map);
	}
}

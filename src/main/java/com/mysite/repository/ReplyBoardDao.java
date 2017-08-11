package com.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.ReplyBoardVo;

@Repository
public class ReplyBoardDao {
	@Autowired
	SqlSession sqlSession;
	
	public int insert(ReplyBoardVo vo) {
		return sqlSession.insert("replyboard.insert", vo);
	}

}

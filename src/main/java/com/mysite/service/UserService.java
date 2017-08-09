package com.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.UserDao;
import com.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	UserDao userDao;
	
	public int join(UserVo userVo) {
		return userDao.insert(userVo);
	}
}

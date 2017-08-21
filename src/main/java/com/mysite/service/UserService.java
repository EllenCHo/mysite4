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
	
	public UserVo getUser(String email, String password) {
		return userDao.getUser(email, password);
	}
	
	public UserVo getUser(int no) {
		return userDao.getUser(no);
	}
	
	public int updateUser(UserVo userVo) {
		return userDao.updateUser(userVo);
	}
	
	public String checkEmail(String email) {
		return userDao.checkEmail(email);
	}
}

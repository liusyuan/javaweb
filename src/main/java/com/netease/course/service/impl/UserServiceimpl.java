package com.netease.course.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.course.dao.UserDao;
import com.netease.course.meta.User;
import com.netease.course.service.UserService;

@Service
public class UserServiceimpl implements UserService{

	@Autowired
	UserDao dao;
	@Override
	public User getUser(String userName) {
		User user=dao.getUser(userName);
		return user;
	}

}

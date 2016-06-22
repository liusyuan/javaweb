package com.netease.course.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.netease.course.dao.UserDao;
import com.netease.course.meta.User;
import com.netease.course.service.LoginService;
import com.netease.course.utils.Status;



@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private HttpSession session;
	
	
	@Autowired
	private UserDao dao;

	@Override
	public Status doLogin(String userName, String password) {
		
		
		try {

			User user = dao.getUser(userName);

			if (user == null) {
				return Status.loginError("用户不存在");
			}

			else if (user.getPassword().equals(password) && user.getUserName().equals(userName)) {
					addSession(session,user);
					return Status.OK;							
			}

			else {
				return Status.loginError("密码错误");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return Status.loginError("未知错误");
		}

	}
	
	public void addSession(HttpSession session,User user){

		session.setAttribute("user", user);
		
	}

}

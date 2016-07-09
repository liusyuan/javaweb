package com.netease.course.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.netease.course.meta.User;
import com.netease.course.service.LoginService;
import com.netease.course.dao.UserDao;
import com.netease.course.utils.Status;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserDao dao;

	@Override
	public Status doLogin(HttpServletRequest req) {


			String userName = req.getParameter("userName");
			String password = req.getParameter("password");
			HttpSession session = req.getSession();
			User user = dao.getUser(userName);

			if (user == null) {
				return Status.error("用户不存在");
			}

			else if (user.getPassword().equals(password) && user.getUserName().equals(userName)) {

				session.setAttribute("user", user);
				return Status.LOGIN_SUCCEESS;
			}

			else {
				return Status.error("密码错误");
			}



	}


}

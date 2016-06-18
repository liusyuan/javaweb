package com.netease.course.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.netease.course.dao.UserDao;
import com.netease.course.meta.User;
import com.netease.course.service.LoginService;


@Service
public class LoginServiceImpl implements LoginService {
	HttpServletRequest req;
	HttpServletResponse resp;
	@Autowired
	public LoginServiceImpl(HttpServletRequest req,HttpServletResponse resp){
		this.req=req;
		this.resp=resp;
	}
	/*
	 * 定义返回参数,返回json数据，格式为{code:xxx,message:xxx,result:xxx}
	 */
	public final static String SUCCESS = "{\"code\":200,\"message\":\"登陆成功\",\"result\":true}";
	final String PASSWORD_WRONG = "{\"code\":400,\"message\":\"密码错误\",\"result\":false}";	
	final String USER_NOT_EXIST = "{\"code\":401,\"message\":\"用户名不存在\",\"result\":false}";
	final String UNKNOWN_MISSTAKE = "{\"code\":401,\"message\":\"登陆失败\",\"result\":false}";

	protected User user;
	@Autowired
	private UserDao dao;

	@Override
	public String doLogin(String userName, String password) {
		
		
		try {

			User user = dao.getUser(userName);

			if (user == null) {
				
				return USER_NOT_EXIST;
			}

			else if (user.getPassword().equals(password) && user.getUsername().equals(userName)) {

				
				addSession(req,resp,user);

				return SUCCESS;

			}

			else {
				return PASSWORD_WRONG;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return UNKNOWN_MISSTAKE;
		}

	}
	
	public void addSession(HttpServletRequest req,HttpServletResponse resp,User user){

		
		HttpSession session=req.getSession();
		if(session.getAttribute("user")!=null){
			System.out.println("已经登陆");
		}
		session.setAttribute("user", user);
		

		
		
	}

}

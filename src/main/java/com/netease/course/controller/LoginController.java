package com.netease.course.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.netease.course.meta.User;
import com.netease.course.service.LoginService;





@Controller
public class LoginController {
 
	User user;
	@Autowired
	LoginService login;
	@RequestMapping(value="/")
	public String indexPage(){
		return "index";		
	}
	
	@RequestMapping("/login")
	public String loginPage(){
		
		return "login";
	}
	
	@RequestMapping(value="/api/login",produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String doLogin(HttpServletRequest req,HttpServletResponse resp){
		
		String userName =req.getParameter("userName");
		String password=req.getParameter("password");

		String result=login.doLogin(userName, password);

		return result;
		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "login";
	}
}

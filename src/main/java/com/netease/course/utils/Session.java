package com.netease.course.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.netease.course.meta.User;

public class Session {
	
	private HttpSession session;
	
	Session(){
		HttpServletRequest req;

	}
	public void add(User user){

		
		if(session.getAttribute("user")!=null){
			System.out.println("已经登陆");
		}
		session.setAttribute("user", user);
		

		
		
	}
}

package com.netease.course.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netease.course.service.SessionService;

public class SessionServiceimpl implements SessionService {

	@Override
	public void addSession(HttpServletRequest req, HttpServletResponse resp) {
		String userName=req.getParameter("userName");
		Cookie cookie=new Cookie("userName",userName);
		resp.addCookie(cookie);
		
		/*设置cookie有效期为2小时*/
		cookie.setMaxAge(2*60);
		
		
	}

}

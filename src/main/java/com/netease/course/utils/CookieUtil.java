package com.netease.course.utils;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;


public class CookieUtil {
	
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;
	public void setCookie(String userName){
		Cookie cookie=new Cookie("userName",userName);
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
	}
	
	public void getCookie(String userName){
		Cookie[] cookies=request.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals(userName)){
					
				}
			}
		}
	}
}

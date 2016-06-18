package com.netease.course.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SessionService {
	
	public void addSession(HttpServletRequest req,HttpServletResponse resp);
}

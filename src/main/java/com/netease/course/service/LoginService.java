package com.netease.course.service;

import javax.servlet.http.HttpServletRequest;

import com.netease.course.utils.Status;

public interface LoginService {
	public Status doLogin(HttpServletRequest req);
}

package com.netease.course.service;

import com.netease.course.utils.Status;

public interface LoginService {
	public Status doLogin(String userName,String password);
}

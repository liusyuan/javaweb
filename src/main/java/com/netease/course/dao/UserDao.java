package com.netease.course.dao;


import org.apache.ibatis.annotations.ResultMap;


import com.netease.course.meta.User;


public interface UserDao {

	@ResultMap(value="userMapper")
	public User getUser(String userName);
	
}

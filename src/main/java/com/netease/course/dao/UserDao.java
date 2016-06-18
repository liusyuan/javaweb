package com.netease.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.netease.course.meta.User;


public interface UserDao {
	
	@Select("select * from person where userName= #{userName}")
	public User getUser(String userName);
	
}

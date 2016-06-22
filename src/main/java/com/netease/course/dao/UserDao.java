package com.netease.course.dao;

import java.util.List;


import org.apache.ibatis.annotations.ResultMap;


import com.netease.course.meta.User;


public interface UserDao {



//	@Select("select p.id as personId,userName,password,nickName,usertype,"
//			+ "c.id as contentId,title,c.price as price,time,icon"
//			+ " from person p left join trx on p.id = trx.personId "
//			+ "left join content c on c.id=trx.contentId where userName= #{userName}")
	@ResultMap(value="userMapper")
	public User getUser(String userName);
	
}

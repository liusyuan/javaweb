package com.netease.course.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netease.course.dao.UserDao;
import com.netease.course.meta.User;
/**
 * 测试UserDao是否正常
 * @author summer
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-mybatis.xml")
public class TestGetUser {


		@Autowired
		private UserDao dao;
		
		@Test
		public void getUser()throws Exception{
			User user=dao.getUser("buyer");
			System.out.println(user.getUserName()+": "+user.getUsertype()+" "+user.getPassword());
		}
		
	

}

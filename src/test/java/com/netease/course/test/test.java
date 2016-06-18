package com.netease.course.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netease.course.dao.UserDao;
import com.netease.course.meta.User;

public class test {
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-mybatis.xml");
		UserDao dao =context.getBean("personDao",UserDao.class);
		User person=dao.getUser("buyer");
		System.out.println(person.getPassword());
	}
}

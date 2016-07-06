package com.netease.course.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netease.course.dao.UserDao;
import com.netease.course.meta.BuyList;
import com.netease.course.meta.User;
import com.netease.course.service.BuyListService;
import com.netease.course.test.service.TestBuyListService;

/**
 * 测试事务
 * @author summer
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:test-mybatis.xml")
public class TestTransaction {

	@Autowired
	private TestBuyListService buyListService;
	@Autowired
	private UserDao dao;
	
	/**
	 * 测试事务
	 * 
	 */
	@Test
	public void test() throws Exception {
		User user=dao.getUser("buyer");
		List<BuyList> buyList=buyListService.getBuyList(user);
		System.out.println(buyList.get(0).getTitle()+buyList.get(0).getBuyTime());

		buyListService.buy(buyList);
		

	}
}

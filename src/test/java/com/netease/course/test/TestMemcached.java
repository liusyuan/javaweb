package com.netease.course.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.whalin.MemCached.MemCachedClient;

import net.sf.json.JSONObject;

/**
 * 测试memcached
 * @author summer
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:memcached.xml")
public class TestMemcached{
	@Autowired
	private MemCachedClient memCachedClient;
	@Test
	public void testAdd() throws Exception{

		memCachedClient.set("netease", "网易");
		System.out.println(memCachedClient.get("com.netease.course.service.impl.ProductServiceImpl.getProduct.10"));
//		JSONObject json=JSONObject.fromObject(memCachedClient.statsItems());
//		System.out.println(json);
//		JSONObject json2=JSONObject.fromObject(memCachedClient.statsCacheDump(8, 0));
//		System.out.println(json2);
//		System.out.println();memCachedClient.statsSlabs();
//		memCachedClient.statsCacheDump(slabNumber,0);
	}
	
}

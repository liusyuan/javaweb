package com.netease.course.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netease.course.meta.Product;
import com.netease.course.service.ProductService;

/**
 * 测试ProductDao是否正常
 * @author summer
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-mybatis.xml")
public class TestProduct {
	@Autowired
	private ProductService productService;
	
	
	@Test
	public void testGetProducts(){
		List<Product> products=productService.getProductList();
		System.out.println("testGetProducts:"+products.get(0).getTitle());
	}

}

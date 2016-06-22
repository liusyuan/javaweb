package com.netease.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.course.dao.ProductDao;
import com.netease.course.service.LoginService;
import com.netease.course.service.TransactionService;
import com.netease.course.utils.Status;

@Controller
@RequestMapping(value="/api",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
public class ApiController {
	
	@Autowired
	private LoginService login;
	@Autowired
	private ProductDao dao;
	@Autowired
	private TransactionService trx;
	
	@RequestMapping(value = "/login")
	@ResponseBody
	public Status doLogin(@RequestParam String userName, @RequestParam String password) {
		
		return login.doLogin(userName, password);
	}
	
	@RequestMapping(value="/buy")
	@ResponseBody
	public Status buy(@RequestParam int id){
		trx.buy(id);
		return Status.OK;
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Status delete(@RequestParam int id){
		try{
			dao.deleteProduct(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Status.OK;
	}
	
	
}

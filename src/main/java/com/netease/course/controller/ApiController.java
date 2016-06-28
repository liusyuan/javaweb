package com.netease.course.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.course.dao.ProductDao;
import com.netease.course.dao.UserDao;
import com.netease.course.meta.BuyList;
import com.netease.course.meta.User;
import com.netease.course.service.LoginService;
import com.netease.course.service.BuyListService;
import com.netease.course.utils.Status;

@Controller
@RequestMapping(value="/api",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
public class ApiController {
	
	@Autowired
	private LoginService login;
	@Autowired
	private ProductDao dao;
	@Autowired
	private BuyListService trx;
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/login")
	@ResponseBody
	public Status doLogin(@RequestParam String userName, @RequestParam String password) {
		
		return login.doLogin(userName, password);
	}
	
	@RequestMapping(value="/buy")
	@ResponseBody
	public Status buy(@RequestBody List<BuyList> buyList,HttpSession session){
		System.out.println("doBuy");
		trx.buy(buyList);
		User user=(User) session.getAttribute("user");
		User newUser=userDao.getUser(user.getUserName());
		session.setAttribute("user",newUser);
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
	
	@RequestMapping(value="/upload")
	@ResponseBody
	public Status upload(){

		
		return Status.OK;
	}
	
	
}

package com.netease.course.controller;

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.netease.course.dao.ProductDao;
import com.netease.course.dao.UserDao;
import com.netease.course.meta.BuyList;
import com.netease.course.meta.User;
import com.netease.course.service.LoginService;
import com.netease.course.service.PictureService;
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
	@Autowired
	private PictureService picService;
	
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
	public Model upload(@RequestPart("file") MultipartFile pic,Model map,HttpServletRequest req) throws IOException{
		String realPath=req.getServletContext().getRealPath("/");		
		String path=picService.save(pic, realPath);

		map.addAttribute("code",200);
		map.addAttribute("message","上传成功");
		map.addAttribute("result",path);

		return map;
	}

	
	public static void main(String[] args) {
		String path="/image";
		File file=new File(path);
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    System.out.println("//不存在");  
		    file .mkdir();    
		} 
	}
}

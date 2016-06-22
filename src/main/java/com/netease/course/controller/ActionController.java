package com.netease.course.controller;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.netease.course.dao.ProductDao;
import com.netease.course.dao.TransactionDao;
import com.netease.course.dao.UserDao;
import com.netease.course.meta.BuyList;
import com.netease.course.meta.Product;
import com.netease.course.meta.User;
import com.netease.course.utils.getIsBuyProduct;

@Controller
public class ActionController {

	@Autowired
	private ProductDao dao;
	
	@Autowired
	private getIsBuyProduct getProduct;

	
	@RequestMapping(value = "/index")
	public String indexPage(Model map,HttpSession session) {

		List<Product> productList=getProduct.setProductList();
		map.addAttribute("productList", productList);
		
		return "index";
	}
	@RequestMapping(value = "/")
	public String index(Model map) {

		List<Product> productList=getProduct.setProductList();
		map.addAttribute("productList", productList);
		return "index";
	}
	
	@RequestMapping("/show")
	public String show(@RequestParam int id,Model map){
		
		Product product=dao.getProduct(id);
		product=getProduct.set(id);
		map.addAttribute("product",product);
		return "show";
	}

	@RequestMapping("/public")
	public String publish() {

		return "public";
	}

	@RequestMapping(value = "/publicSubmit", method = RequestMethod.POST)
	public String publicSubmit(Product product) {
		try {
			dao.publishProduct(product);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "publicSubmit";
	}

	@RequestMapping("/edit")
	public String edit(@RequestParam int id,Model map) {
		Product product=dao.getProduct(id);
		map.addAttribute("product",product);
		return "edit";
	}

	@RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
	public String editSumbmit(Product product) {
		
		try {
			dao.updateProduct(product);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editSubmit";
	}
	
	@RequestMapping("/account")
	public String account(HttpSession session,Model map){
		User user=(User) session.getAttribute("user");
		List<BuyList> buyList=user.getBuyList();
		map.addAttribute("buyList",buyList);		
		return "account";
	}
	




}

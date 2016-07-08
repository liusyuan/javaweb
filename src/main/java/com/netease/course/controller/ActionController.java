package com.netease.course.controller;


import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netease.course.meta.User;
import com.netease.course.service.BuyListService;
import com.netease.course.service.ProductService;
import com.netease.course.meta.BuyList;
import com.netease.course.meta.Product;

@Controller
@RequestMapping(produces = "text/html;charset=UTF-8")
public class ActionController {

	@Autowired
	private ProductService productService;

	@Autowired
	private BuyListService buyListService;

	// undefined路径是为了解决在Firefox下购物车页点击退出按钮进入到undefined路径的bug
	@RequestMapping(value = { "/index", "/", "/undefined" })
	public String indexPage(Model map) {

			List<Product> productList = productService.getProductList();
			map.addAttribute("productList", productList);

		return "index";
	}

	@RequestMapping("/show")
	public String show(@RequestParam int id, Model map) {

		Product product = productService.getProduct(id);
		map.addAttribute("product", product);
		return "show";
	}

	@RequestMapping("/public")
	public String publish() {

		return "public";
	}

	@RequestMapping(value = "/publicSubmit", method = RequestMethod.POST)
	public String publicSubmit(Product product,Model map) {
		try {
			
			productService.addProduct(product);

		} catch (Exception e) {
			map.addAttribute("product",null);
			e.printStackTrace();
		}
		return "publicSubmit";
	}

	@RequestMapping("/edit")
	public String edit(@RequestParam int id, Model map) {
		Product product = productService.getProduct(id);
		map.addAttribute("product", product);
		return "edit";
	}

	@RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
	public String editSumbmit(Product product,Model map) {

		try {
			productService.updateProduct(product);

		} catch (Exception e) {
			
			map.addAttribute("product",null);
			e.printStackTrace();
		}
		return "editSubmit";
	}

	@RequestMapping("/account")
	public String account(HttpSession session, Model map) {

		try {
			User user = (User) session.getAttribute("user");
			String userName=user.getUserName();
			if (user != null) {
				List<BuyList> buyList = buyListService.getBuyList(userName);
				map.addAttribute("buyList", buyList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "account";
	}

	@RequestMapping("/settleAccount")
	public String settleAccount() {

		return "settleAccount";
	}

}

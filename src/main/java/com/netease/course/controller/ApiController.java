package com.netease.course.controller;

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

import com.netease.course.meta.User;
import com.netease.course.service.LoginService;
import com.netease.course.service.ProductService;
import com.netease.course.service.UserService;
import com.netease.course.meta.BuyList;
import com.netease.course.service.BuyListService;
import com.netease.course.utils.BuyException;
import com.netease.course.utils.PictureUtil;
import com.netease.course.utils.Status;

@Controller
@RequestMapping(value = "/api", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
public class ApiController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private ProductService productService;
	@Autowired
	private BuyListService trx;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login")
	@ResponseBody
	public Status doLogin(HttpServletRequest req) {
		try{
			Status status=loginService.doLogin(req);
			return status;
		}catch(Exception e){
			e.printStackTrace();
			return Status.error("未知错误");
		}
		
	}

	@RequestMapping(value = "/buy")
	@ResponseBody
	public Status buy(@RequestBody List<BuyList> buyList, HttpSession session) throws Exception {
		
		try {
			trx.addBuyList(buyList);
			User user = (User) session.getAttribute("user");
			User newUser = userService.getUser(user.getUserName());
			session.setAttribute("user", newUser);
			return Status.ok("购买成功");
		}catch(BuyException e){ 
			return Status.error(e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return Status.error("购买失败");
		}

	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public Status delete(@RequestParam int id) {

		try {
			productService.deleteProduct(id);
			return Status.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Status.error("删除失败");
		}

	}

	@RequestMapping(value = "/upload")
	@ResponseBody
	public Model upload(@RequestPart("file") MultipartFile pic, Model map, HttpServletRequest req) {
		try {
			String realPath = req.getServletContext().getRealPath("/");

			String path = PictureUtil.save(pic, realPath);

			String picPath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path;

			map.addAttribute("code", 200);
			map.addAttribute("message", "upload success");
			map.addAttribute("result", picPath);
			
			return map;

		} catch (Exception e) {
			e.printStackTrace();
			map.addAttribute("code", 500);
			map.addAttribute("message", "upload false");
			map.addAttribute("result", null);
			
			return map;

		}
	}

}

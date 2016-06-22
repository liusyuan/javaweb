package com.netease.course.controller;


import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

	@RequestMapping("/login")
	public String loginPage(HttpSession session) {

		if (session.getAttribute("user") != null) {
			return "index";
		}
		return "login";
	}


	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
}

package com.netease.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ActionController {
	
	@RequestMapping("/public")
	public String publish(){
		
		return "public";
	}
}

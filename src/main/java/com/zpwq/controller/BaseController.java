package com.zpwq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
	@RequestMapping("/**")	
	public String execute(){
		return "index";
	}
	@ExceptionHandler
	public String error(Exception e){
		System.out.println("控制器发生异常");
		e.printStackTrace();
		return "index";
	}

}

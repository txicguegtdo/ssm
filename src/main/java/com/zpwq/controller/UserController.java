package com.zpwq.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zpwq.bean.User;
import com.zpwq.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	@Resource
	private UserService userServer;
	
	@RequestMapping("/login")
	public String login(){
		return "index";
	}
	@RequestMapping(value="/toLogin",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String toLogin(User user,HttpSession session){
		if(userServer.login(user)){
			session.setAttribute("user", userServer.getUesr(user));
			System.out.println("UserControlle::::用户登录成功:"+((User)session.getAttribute("user")).toString());
			return "{\"flag\":1,\"html\":\"task/list\"}";
		}else{
			System.out.println("UserControlle::::用户登录失败:"+user.toStirng());
			return "{\"flag\":0}";
		}
	}
	@RequestMapping(value="/register",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String register(User user){
		int id = userServer.register(user);
		if(id > 0){
			return "{\"flag\":1,\"id\":"+id+"}";
		}
		return "{\"flag\":0}";
	}
	
}

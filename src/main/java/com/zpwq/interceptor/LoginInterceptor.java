package com.zpwq.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{


	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(request.getSession().getAttribute("user")!=null){
			response.setContentType("text/html;charset=UTF-8");
			return true;
		}
		System.out.println("进入拦截器:LoginInterceptor,方法:preHandle");
		response.sendRedirect(request.getSession().getServletContext().getContextPath()+
				"/user/login");
		return false;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("进入拦截器:LoginInterceptor,方法:afterCompletion");
		
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("进入拦截器:LoginInterceptor,方法:afterCompletion");

	}

}

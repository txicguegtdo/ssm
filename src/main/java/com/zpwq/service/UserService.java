package com.zpwq.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zpwq.bean.User;
import com.zpwq.dao.UserDAO;

@Service
public class UserService {
	@Resource
	private UserDAO userDAO;
	
	public Boolean login(User user){
		if(user== null || user.getName() == null || user.getPassword()==null){
			return false;
		}
		User data = getUesr(user);
		return data!=null; 
	}
	public User getUesr(User user){
		return (User)userDAO.getBeanByNameAndPwd(user);
	}
	
	public int register(User user){
		if(user == null || user.getName()==null){
			return 0;
		}
		User data = (User)userDAO.getBeanByName(user.getName());
		if(data !=null){
			return 0;
		}
		if(userDAO.addBean(user)){
			return user.getId();
		}
		return 0;
	}
	
}

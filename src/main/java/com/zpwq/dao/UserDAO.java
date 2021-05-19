package com.zpwq.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.zpwq.bean.Bean;
import com.zpwq.bean.User;
@Repository(value="userDAO")
public class UserDAO implements DAO{
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	public boolean addBean(Bean bean) {
		return sqlSessionTemplate.insert("com.zpwq.dao.UserDAO.addUser", bean)>0;
	}
	public Bean getBeanById(int id) {
		return sqlSessionTemplate.selectOne("com.zpwq.dao.UserDAO.getUser", id);
	}
	public Bean getBeanByNameAndPwd(User user){
		return sqlSessionTemplate.selectOne("com.zpwq.dao.UserDAO.getUserByNameAndPwd", user);
	}
	public Bean getBeanByName(String name){
		return sqlSessionTemplate.selectOne("com.zpwq.dao.UserDAO.getUserByName", name);
	}
	public boolean updateBean(Bean bean) {

		return false;
	}
	public boolean delBean(int id) {
		return false;
	}
	

}

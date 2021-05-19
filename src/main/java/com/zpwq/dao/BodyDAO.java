package com.zpwq.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.zpwq.bean.Bean;
import com.zpwq.bean.Body;
@Repository(value="bodyDAO")
public class BodyDAO implements DAO{
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	public boolean addBean(Bean bean) {
		return sqlSessionTemplate.insert("com.zpwq.dao.BodyDAO.addBody", bean)>0;
	}

	public boolean updateBean(Bean bean) {
		return sqlSessionTemplate.update("com.zpwq.dao.BodyDAO.updateBody", bean)>0;
	}
	
	public Bean getBeanById(int id) {
		return sqlSessionTemplate.selectOne("com.zpwq.dao.BodyDAO.getBody", id);
	}
	public List<Body> getBeanByTaskId(int taskId) {
		return sqlSessionTemplate.selectList("com.zpwq.dao.BodyDAO.getBodyByTaskId", taskId);
	}
	public boolean delBean(int id) {
		return sqlSessionTemplate.update("com.zpwq.dao.BodyDAO.delBody", id)>0;
	}

}

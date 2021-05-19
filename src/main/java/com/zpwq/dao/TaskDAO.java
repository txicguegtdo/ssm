package com.zpwq.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.zpwq.bean.Bean;
import com.zpwq.bean.Task;
@Repository(value="taskDAO")
public class TaskDAO implements DAO{
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	public boolean addBean(Bean bean) {
		return sqlSessionTemplate.insert("com.zpwq.dao.TaskDAO.addTask",bean)>0;
	}
	public boolean updateBean(Bean bean){
		return sqlSessionTemplate.update("com.zpwq.dao.TaskDAO.updateTask", bean)>0;
	}
	
	public Bean getBeanById(int id) {
		return sqlSessionTemplate.selectOne("com.zpwq.dao.TaskDAO.getTask", id);
	}
	public List<Task> getTaskByUser(int id){
		return sqlSessionTemplate.selectList("com.zpwq.dao.TaskDAO.getTaskByUser", id);
	}
	
	public boolean delBean(int id){
		return sqlSessionTemplate.update("com.zpwq.dao.TaskDAO.delTask", id)>0;
	}
	
}

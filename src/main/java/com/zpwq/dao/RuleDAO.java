package com.zpwq.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.zpwq.bean.Bean;
import com.zpwq.bean.Rule;
@Repository(value="ruleDAO")
public class RuleDAO implements DAO{
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	public boolean addBean(Bean bean) {
		return sqlSessionTemplate.insert("com.zpwq.dao.RuleDAO.addRule", bean)>0;
	}

	public boolean updateBean(Bean bean) {
		return sqlSessionTemplate.update("com.zpwq.dao.RuleDAO.updateRule", bean)>0;
	}
	
	public Bean getBeanById(int id) {
		return sqlSessionTemplate.selectOne("com.zpwq.dao.RuleDAO.getRule", id);
	}
	
	public List<Rule> getRuleByBody(int id){
		return sqlSessionTemplate.selectList("com.zpwq.dao.RuleDAO.getRuleByBodyId", id);
	}
	public boolean delBean(int id) {
		return sqlSessionTemplate.update("com.zpwq.dao.RuleDAO.delRule", id)>0;
	}
	public List<Rule> getRuleByTaskId(int id){
		return sqlSessionTemplate.selectList("com.zpwq.dao.RuleDAO.getRuleByTaskId", id);
	}
	
}

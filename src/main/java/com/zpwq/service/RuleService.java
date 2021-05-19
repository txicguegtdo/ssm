package com.zpwq.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zpwq.bean.Bean;
import com.zpwq.bean.Rule;
import com.zpwq.dao.RuleDAO;

@Service
public class RuleService {
	
	@Resource
	private RuleDAO ruleDAO;

	public List<Rule> getRulesByTaskId(int taskId){
		return ruleDAO.getRuleByTaskId(taskId);
	}
	
	public Rule getRuleById(int id){
		return (Rule)ruleDAO.getBeanById(id);
	}
	public boolean addRule(Bean bean){
		return ruleDAO.addBean(bean);
	}
	public boolean upDateRule(Bean bean){
		return ruleDAO.updateBean(bean);
	}
	
}

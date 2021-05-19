package com.zpwq.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zpwq.bean.Body;
import com.zpwq.dao.BodyDAO;
@Service
public class BodyService {
	@Resource
	private BodyDAO bodyDAO;
	public boolean addBody(Body body){
		return bodyDAO.addBean(body);
	}
	public List<Body> getBodyByTaskId(int taskId){
		return bodyDAO.getBeanByTaskId(taskId);
	}
	public boolean updateBody(Body body){
		return bodyDAO.updateBean(body);
	}
	
}

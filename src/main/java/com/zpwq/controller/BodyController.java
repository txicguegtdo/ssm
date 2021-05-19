package com.zpwq.controller;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.alibaba.fastjson.JSONObject;
import com.zpwq.bean.Body;
import com.zpwq.bean.Rule;
import com.zpwq.service.BodyService;
import com.zpwq.service.RuleService;
import com.zpwq.util.DateUtil;
import com.zpwq.util.FileUtils;

@Controller
@RequestMapping("/body")
public class BodyController extends BaseController{
	@Resource
	private BodyService bodyService;
	@Resource
	private RuleService ruleService;
	
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	@RequestMapping(value="/getByTaskId",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getBody(int taskId){
		JSONObject cp = new JSONObject();
		List<Body> list = bodyService.getBodyByTaskId(taskId);
		List<Rule> rules = ruleService.getRulesByTaskId(taskId);
		
		cp.put("list", list);
		cp.put("rule", rules);
		return cp.toString();
	}
	@RequestMapping(value="/uploadRule",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String upLoadRule(Rule rule){
		JSONObject cp = new JSONObject();
		Boolean flag = false;
		if(rule.getId()>0){
			Rule bean = ruleService.getRuleById(rule.getId());
			bean.setRuleCode(rule.getRuleCode());
			bean.setRuleType(rule.getRuleType());
			flag = ruleService.upDateRule(bean);
		}else{
			flag = ruleService.addRule(rule);
		}
		cp.put("flag", flag?1:0);
		cp.put("msg", flag?"保存成功！":"保存失败！");
		return cp.toJSONString();
	}
	
	
	@RequestMapping(value="/upload",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String  fileUpLoad(Body body,@RequestParam("file") MultipartFile file){
		JSONObject cp = new JSONObject();
		cp.put("flag", "1");
		cp.put("msg", "success");
			
        if (!file.isEmpty()) {
        	try{
        		byte[] bytes = file.getBytes();
                String name = file.getOriginalFilename();
                System.out.println(name);
                int index = name.lastIndexOf(".");
                if(index>0){
                	name = name.substring(index+1);
                }else{
                	name = "temp";
                }
                if("0".equals(body.getBodyType())){
                	if(!"txt".equalsIgnoreCase(name)){
                		throw new Exception("file is not a txt ");
                	}
                }else if("1".equals(body.getBodyType())){
                	if(  !"xls".equalsIgnoreCase(name) 
                	  && !"xlsx".equalsIgnoreCase(name)){
                		throw new Exception("file is not xls or xlsx ");
                	}
                }
                String path = filePath+DateUtil.getDateStr()+"."+name;
                System.out.println(path);
                FileUtils.saveFile(path, bytes);
                if(body.getId()>0){
                	FileUtils.delFile(body.getPath());
                	body.setPath(path);
                	bodyService.updateBody(body);
                }else{
                	body.setPath(path);
                	bodyService.addBody(body);
                }
                cp.put("body", body);
        	}catch(Exception e){
        		cp.put("falg", 0);
            	cp.put("msg", "fail:"+e.getMessage());
        	}
            
        } else {
        	cp.put("flag", 2);
        	cp.put("msg", "file is empty");
        }
        System.out.println(cp);
		return cp.toJSONString();
	}

}

package com.zpwq.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zpwq.bean.Task;
import com.zpwq.bean.User;
import com.zpwq.service.TaskService;
import com.zpwq.util.MailUtil;

@Controller
@RequestMapping("/task")
public class TaskController extends BaseController{
	@Resource
	private TaskService taskService;
	
	
	@RequestMapping("/list")
	public String list(HttpSession session,HttpServletRequest req){
		User user = (User)session.getAttribute("user");
		List<Task> list =  taskService.getTaskByUser(user.getId());
		req.setAttribute("taskList", list);
		return "task";
	}
	
	@RequestMapping(value="/save",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String save(Task task,HttpSession session){
		JSONObject cp = new JSONObject();
		boolean flag = false;
		if(task.getId()>0){
			flag = taskService.updateTask(task);
		}else{
			User user = (User)session.getAttribute("user");
			task.setCreater(user.getId());
			flag = taskService.addTask(task);
		}
		cp.put("flag", flag?1:0);
		cp.put("msg", flag?"保存成功！":"保存失败！");
		cp.put("id", task.getId());
		return cp.toJSONString();
	}
	@RequestMapping(value="/del",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String del(int taskId){
		JSONObject cp = new JSONObject();
		boolean flag = taskService.delTask(taskId);
		cp.put("flag", flag?1:0);
		cp.put("msg", flag?"删除成功！":"删除失败！");
		return cp.toJSONString();
	}
	
	@RequestMapping("/emailValidate")
	@ResponseBody
	public String emailValidate(Task task){
		JSONObject cp = new JSONObject();
		if(task.getId() > 0 ){
			task = taskService.getTask(task.getId());
		}
		MailUtil mail = new MailUtil(task.getEmail(),task.getPwd(),task.getHost());
		boolean flag = false;
		try{
			flag = mail.validate();
			cp.put("msg", flag?"邮箱配置正确！":"邮箱配置错误！");
		}catch(Exception e){
			cp.put("msg", "邮箱配置错误,原因："+e.getMessage());
		}
		cp.put("flag", flag?1:0);
		return cp.toJSONString();
	}
	
	@RequestMapping("/send")
	@ResponseBody
	public String send(int taskId){
		JSONObject cp = new JSONObject();
		boolean flag = taskService.send(taskId);
		cp.put("flag", flag?1:0);
		cp.put("msg", flag?"发送成功！":"发送失败！");
		return cp.toJSONString();
	}
	
	@RequestMapping("/work")
	@ResponseBody
	public String work(int taskId,String index){
		JSONObject cp = new JSONObject();
		Task task = taskService.getTask(taskId);
		task.setExtend1(index);
		boolean flag = taskService.updateTask(task);
		cp.put("flag", flag?1:0);
		cp.put("msg", flag?"更新成功！":"更新失败！");
		cp.put("index", index);
		return cp.toJSONString();
	}
	
	
}

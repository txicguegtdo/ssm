package com.zpwq.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.springframework.stereotype.Service;

import com.zpwq.bean.Body;
import com.zpwq.bean.Rule;
import com.zpwq.bean.Task;
import com.zpwq.dao.BodyDAO;
import com.zpwq.dao.RuleDAO;
import com.zpwq.dao.TaskDAO;
import com.zpwq.util.DateUtil;
import com.zpwq.util.ExeclUtil;
import com.zpwq.util.FileUtils;
import com.zpwq.util.MailUtil;

@Service
public class TaskService {
	@Resource
	private TaskDAO taskDAO;
	@Resource
	private RuleDAO ruleDAO;
	@Resource
	private BodyDAO bodyDAO;

	public List<Task> getTaskByUser(int userId) {
		return taskDAO.getTaskByUser(userId);
	}

	public Task getTask(int id) {
		return (Task) taskDAO.getBeanById(id);
	}

	public boolean addTask(Task task) {
		return taskDAO.addBean(task);
	}

	public boolean updateTask(Task task) {
		return taskDAO.updateBean(task);
	}
	
	
	public boolean delTask(int id) {
		return taskDAO.delBean(id);
	}

	public boolean workAll(int userId) {
		List<Task> list = getTaskByUser(userId);
		if (list == null || list.size() == 0) {
			return true;
		}

		return true;
	}

	public boolean work(Task task) {
		if (task == null) {
			System.out.println("当前任务为空！");
		}

		if (task.getJobType() == 0) {
			System.out.println("当前任务[" + task.getId() + "]不需要执行！");
		} else if (task.getJobType() == 1) {// 定时发送
			
		} else if (task.getJobType() == 2) {// 周期性发送，目前支持每月最后一个工作日上午九点发送
			long time = DateUtil.getDateFromyyyyMMddHHmm(task.getJobTime());
			if (time != -1 && DateUtil.getNowTimeLong() > time) {// 可以发送邮件
				send(task);
				task.setJobTime("");
			}
			
		}

		return true;
	}
	public boolean send(int taskId){
		Task task = (Task)taskDAO.getBeanById(taskId);
		return send(task);
	}
	public boolean send(Task task) {
		// 获取规则
		List<Rule> rules = ruleDAO.getRuleByTaskId(task.getId());
		// 处理规则
		if (rules.size() > 0) {
			for (Rule rule : rules) {
				String code = rule.getRuleCode();
				if (code != null && code.startsWith("#{date-") && code.endsWith("}")) {
					rule.setRuleValue(DateUtil.getDateByFormat(rule.getRuleType()));
				} else if ("#{workNum}".equals(code)) {
					rule.setRuleValue(String.valueOf(DateUtil.getWorkNum()));
				}
			}
		}
		List<Body> bodys = bodyDAO.getBeanByTaskId(task.getId());
		String title = "";// 标题
		String mainBody = "";// 正文
		Map<String, String> attachment = new HashMap<String, String>();

		// 获取邮件内容
		if (bodys != null && bodys.size() > 0) {
			for (Body body : bodys) {
				try{
					if ("0".equals(body.getBodyType())) {// 处理正文
						title = replaceAll(body.getName(), rules);
						mainBody = FileUtils.readTextByFile(body.getPath());
						mainBody = replaceAll(mainBody, rules);
					} else if ("1".equals(body.getBodyType())) {// 处理附件
						String path = body.getPath();
						String fileType = path.substring(path.lastIndexOf(".") + 1);
						String name = replaceAll(body.getName(), rules) +"."+ fileType; // 处理附件名
						path = path.substring(0, path.lastIndexOf("/"))+"/temp/"+DateUtil.getDateStr()+"."+fileType ;
						ExeclUtil execl = new ExeclUtil(body.getPath());
						List<HSSFCell> list = execl.getCells();
						for (HSSFCell cell : list) {//处理附件内容
							String value = ExeclUtil.getStringCellValue(cell);
							value = replaceAll(value, rules);
							ExeclUtil.setCellVlaul(cell, value);
						}
						execl.saveExecl(path);
						System.out.println("保存临时附件："+path);
						attachment.put(path, name);
					}
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("邮件内容："+body.getPath()+",处理失败！");
				}
			}
		}
		System.out.println(attachment);
		System.out.println("邮件内容处理完毕！");
		
		// 发送邮件
		MailUtil mail = new MailUtil(task.getEmail(),task.getPwd(),task.getHost());
		mail.sendMessage(task.getToSend(), task.getToCC(), title, mainBody, attachment);
		System.out.println("邮件发送完毕！");
		// 删除临时附件
		for(Entry<String,String> entry : attachment.entrySet()){
			ExeclUtil.delExecl(entry.getKey());
		}
		System.out.println("临时附件删除完成！");
		return true;
	}

	public String replaceAll(String str, List<Rule> rules) {
		if (rules == null || rules.size() == 0 || str == null) {
			return str;
		}
		for (Rule rule : rules) {
			str = str.replaceAll(rule.getRuleCode().replace("{", "\\{")
					.replace("}", "\\}"), rule.getRuleValue());
		}
		return str;
	}

	public static void main(String[] args) {

		

	}

}

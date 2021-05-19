package com.zpwq.bean;

import java.sql.Date;

public class Task extends Bean{
	//主键
	private int id;
	//任务名称
	private String code;
	//任务描述
	private String desc;
	//发送者邮箱
	private String email;
	//邮箱服务器
	private String host;
	//邮箱密码
	private String pwd;
	//要发送邮箱,以,分割
	private String toSend;
	//要抄送邮箱  以,分割
	private String toCC;
	//要密送邮箱 以,分割
	private String toBCC;
	
	//任务类型 默认为0   , 0:立即发送 ,1:定时
	private int jobType;
	//定时任务
	private String jobTime;
	//创建者
	private int creater;
	//创建时间
	private Date createDate;
	//修改时间
	private Date modifyDate;
	
	private String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	private String extend1;
	
	public String getExtend1() {
		return extend1;
	}
	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getToSend() {
		return toSend;
	}
	public void setToSend(String toSend) {
		this.toSend = toSend;
	}
	public String getToCC() {
		return toCC;
	}
	public void setToCC(String toCC) {
		this.toCC = toCC;
	}
	public String getToBCC() {
		return toBCC;
	}
	public void setToBCC(String toBCC) {
		this.toBCC = toBCC;
	}
	public int getJobType() {
		return jobType;
	}
	public void setJobType(int jobType) {
		this.jobType = jobType;
	}
	public String getJobTime() {
		return jobTime;
	}
	public void setJobTime(String jobTime) {
		this.jobTime = jobTime;
	}
	public int getCreater() {
		return creater;
	}
	public void setCreater(int creater) {
		this.creater = creater;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
	
}

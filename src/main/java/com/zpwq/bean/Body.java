package com.zpwq.bean;

import java.sql.Date;

public class Body extends Bean{
	private int id;
	private int taskId;
	private String bodyType;
	private String path;
	private String name;
	private Date modfiyDate;
	private String extend1;
	private String extend2;
	private String flag;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getBodyType() {
		return bodyType;
	}
	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getModfiyDate() {
		return modfiyDate;
	}
	public void setModfiyDate(Date modfiyDate) {
		this.modfiyDate = modfiyDate;
	}
	public String getExtend1() {
		return extend1;
	}
	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	public String getExtend2() {
		return extend2;
	}
	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}

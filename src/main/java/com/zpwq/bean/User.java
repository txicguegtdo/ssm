package com.zpwq.bean;

public class User extends Bean{
	private int id;
	private String name;
	private String password;
	private String extend1;
	private String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getExtend1() {
		return extend1;
	}
	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	public String toStirng(){
		return "User {id:"+this.id+",name:"+this.name+",password:"+this.password+",extend1:"+this.extend1+"}";
	}
	
	
}

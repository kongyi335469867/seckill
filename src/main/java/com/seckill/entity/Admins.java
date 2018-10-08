package com.seckill.entity;

import java.util.Date;

public class Admins {
	
	private long aid;		//管理员ID
	private String aname;	//管理员登录名
	private String apassword;//管理员登录密码
	private Date atime;      //管理员注册时间
	private Integer astate;		 //管理员状态
	private Integer apower;		 //管理员权限
	public long getAid() {
		return aid;
	}
	public void setAid(long aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getApassword() {
		return apassword;
	}
	public void setApassword(String apassword) {
		this.apassword = apassword;
	}
	public Date getAtime() {
		return atime;
	}
	public void setAtime(Date atime) {
		this.atime = atime;
	}
	public Integer getAstate() {
		return astate;
	}
	public void setAstate(Integer astate) {
		this.astate = astate;
	}
	public Integer getApower() {
		return apower;
	}
	public void setApower(Integer apower) {
		this.apower = apower;
	}
	public String toString() {
		return "Admins ["
				+ "aid=" + aid 
				+ ", aname=" + aname 
				+ ", apassword=" + apassword 
				+ ", atime=" + atime 
				+ ", astate=" + astate 
				+ ", apower=" + apower 
				+ "]";
	}
	
}

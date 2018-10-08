package com.seckill.entity;

import java.util.Date;

public class Adlogin {
	
	private long adid;
	private long aid;
	private Date aditime;
	private Date adotime;
	public long getAdid() {
		return adid;
	}
	public void setAdid(long adid) {
		this.adid = adid;
	}
	public long getAid() {
		return aid;
	}
	public void setAid(long aid) {
		this.aid = aid;
	}
	public Date getAditime() {
		return aditime;
	}
	public void setAditime(Date aditime) {
		this.aditime = aditime;
	}
	public Date getAdotime() {
		return adotime;
	}
	public void setAdotime(Date adotime) {
		this.adotime = adotime;
	}
	@Override
	public String toString() {
		return "Adlogin ["
				+ "adid=" + adid 
				+ ", aid=" + aid 
				+ ", aditime=" + aditime 
				+ ", adotime=" + adotime 
				+ "]";
	}
	
}

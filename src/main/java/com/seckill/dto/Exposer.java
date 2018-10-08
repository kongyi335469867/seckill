package com.seckill.dto;

/*数据传输对象：暴露接口地址*/
public class Exposer {
	
	/*是否暴露地址*/
	private boolean exposed;
	private long seckillId;
	private long nowTime;
	private long startTime;
	private long endTime;
	private String md5;
	
	public Exposer(boolean exposed, long seckillId, long nowTime, long startTime, long endTime) {
		this.exposed = exposed;
		this.seckillId = seckillId;
		this.nowTime = nowTime;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public Exposer(boolean exposed, long seckillId, String md5) {
		this.exposed = exposed;
		this.seckillId = seckillId;
		this.md5 = md5;
	}
	public Exposer(boolean exposed, long seckillId) {
		this.exposed = exposed;
		this.seckillId = seckillId;
	}
	public boolean isExposed() {
		return exposed;
	}
	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}
	public long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}
	public long getNowTime() {
		return nowTime;
	}
	public void setNowTime(long nowTime) {
		this.nowTime = nowTime;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	@Override
	public String toString() {
		return "Exposer ["
				+ "exposed=" + exposed 
				+ ", seckillId=" + seckillId 
				+ ", nowTime=" + nowTime 
				+ ", startTime="+ startTime 
				+ ", endTime=" + endTime 
				+ ", md5=" + md5 
				+ "]";
	}
	
}

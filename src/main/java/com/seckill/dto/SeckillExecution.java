package com.seckill.dto;

import com.seckill.entity.SeckillStateEnum;
import com.seckill.entity.SuccessKilled;

/*数据传输对象：封装秒杀后结果*/
public class SeckillExecution {

	private long seckillId;
	/*秒杀状态标识：-1:无效 0:成功 1:已付款 2:已发货*/
	private int state;  
	/*秒杀状态标识信息*/
	private String stateInfo;
	private SuccessKilled successKilled;
	
	public SeckillExecution(long seckillId, SeckillStateEnum stateEum) {
		this.seckillId = seckillId;
		this.state = stateEum.getState();
		this.stateInfo = stateEum.getStateInfo();
	}
	public SeckillExecution(long seckillId, SeckillStateEnum stateEum, SuccessKilled successKilled) {
		this.seckillId = seckillId;
		this.state = stateEum.getState();
		this.stateInfo = stateEum.getStateInfo();
		this.successKilled = successKilled;
	}
	
	public long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}
	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}
	@Override
	public String toString() {
		return "SeckillExecution ["
				+ "seckillId=" + seckillId 
				+ ", state=" + state 
				+ ", stateInfo=" + stateInfo
				+ ", successKilled=" + successKilled 
				+ "]";
	}
	
}

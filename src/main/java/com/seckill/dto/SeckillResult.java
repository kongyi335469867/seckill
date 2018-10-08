package com.seckill.dto;

/*数据传输对象：将所有的ajax请求返回封装后的json数据*/
public class SeckillResult<T> {

	private boolean success;
	private T data;
	private String error;
	/*失败返回构造*/
	public SeckillResult(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}
	/*成功返回构造*/
	public SeckillResult(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "SeckillResult ["
				+ "success=" + success 
				+ ", data=" + data 
				+ ", error=" + error 
				+ "]";
	}
	
}

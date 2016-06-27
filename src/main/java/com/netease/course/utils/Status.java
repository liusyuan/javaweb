package com.netease.course.utils;



public class Status {	
	private int code;
	private String message;
	private boolean result;
	
	public Status(int code,String message,boolean result){
		this.code=code;
		this.message=message;
		this.result=result;
	}
	public Status(){
		
	}
	
	public static Status OK=new Status(200,"请求成功",true);
	public static Status LOGIN_SUCCEESS=new Status(200,"登陆成功",true);
	/**
	 * 请求错误
	 * @param msg 错误信息
	 * @return
	 */
	public static Status Error(String msg){
		return new Status(401,msg,false);
	}


	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public boolean isResult() {
		return result;
	}		
}

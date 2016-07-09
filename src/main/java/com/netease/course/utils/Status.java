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
	
	public static Status LOGIN_SUCCEESS=new Status(200,"登陆成功",true);
	/**
	 * 请求错误
	 * @param msg 错误信息
	 * @return
	 */
	public static Status error(String msg){
		return new Status(401,msg,false);
	}

	/**
	 * 请求成功
	 * @param msg 成功信息
	 * @return
	 */
	public static Status ok(String msg){
		return new Status(200,msg,true);
	}
	
	public static Status ok(int code,String msg){
		return new Status(code,msg,true);
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

package com.netease.course.utils;



public class Status {	
	private static int code;
	private static String message;
	private static boolean result;
	
	public Status(int code,String message,boolean result){
		Status.code=code;
		Status.message=message;
		Status.result=result;
	}
	public Status(){
		
	}

	public static Status OK=new Status(200,"请求成功",true);
	//public static Status Error=new Status(401,"请求失败",false);
	
	/**
	 * 登陆错误
	 * @param msg 错误信息
	 * @return
	 */
	public static Status loginError(String msg){
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

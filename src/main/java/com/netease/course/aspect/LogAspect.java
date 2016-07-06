package com.netease.course.aspect;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;


import com.netease.course.meta.User;
import com.netease.course.utils.Status;

/*
 * aop日志管理
 */

@Aspect
public class LogAspect {
	private static final Logger log=Logger.getLogger(LogAspect.class);
	@Autowired
	private HttpSession session;

    
    
	@AfterReturning(pointcut="execution(* com.netease.course.controller.ApiController.*(..))",returning="status")		
	public void afterReturn(Status status){

		User user=(User) session.getAttribute("user");
		if(user!=null){
			log.info(user.getUserName()+":"+status.getMessage());
		}else{
			log.info(status.getMessage());
		}
	}
	
	/*
	 * 检测用户退出,并输出用户退出登陆信息
	 */
	@AfterReturning(pointcut="execution(* com.netease.course.controller.LoginController.logout(..))")
	public void logout(){
		log.info("退出登陆");
	}
	
	@AfterThrowing(pointcut="execution(* com.netease.course.service..*.*(..))",throwing="e")
	private void afterThrowing(Throwable e){
		log.info("异常");
		log.error(e.toString());
	}
}

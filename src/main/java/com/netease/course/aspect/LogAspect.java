package com.netease.course.aspect;




import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.netease.course.meta.User;
import com.netease.course.utils.Status;


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
	
	@AfterThrowing(pointcut="execution(* com.netease.course.**.*(..))",throwing="e")
	public void afterThrowing(RuntimeException e){
		log.error(e);
	}
}

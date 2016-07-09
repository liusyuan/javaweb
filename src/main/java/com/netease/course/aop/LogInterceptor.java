package com.netease.course.aop;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;


import com.netease.course.meta.User;
import com.netease.course.utils.BuyException;
import com.netease.course.utils.Status;

/*
 * aop日志管理
 */

@Aspect
public class LogInterceptor {
	private static final Logger log=Logger.getLogger(LogInterceptor.class);
	@Autowired
	private HttpSession session;

    
    
	@AfterReturning(pointcut="execution(* com.netease.course.controller.ApiController.*(..))",returning="status")		
	public void doAfterReturn(Status status){

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
		session.invalidate();
		log.info("退出登陆");
	}
	
	@AfterThrowing(pointcut="execution(* com.netease.course.service.*.*.*(..))",throwing="e")
	private void doAfterThrowing(Throwable e) throws Throwable{
		if(e.getClass().equals(BuyException.class)){
			log.info(e.getMessage());
		}else{
			log.error(e.getMessage(),e);
		}

	}
}

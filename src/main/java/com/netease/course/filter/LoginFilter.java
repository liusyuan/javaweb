package com.netease.course.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	private FilterConfig config;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) res;
		HttpSession session=request.getSession();
		
		/*
		 * 定义不过滤路径
		 */
		String noLoginPaths=config.getInitParameter("noLoginPaths");
		if(noLoginPaths!=null){
			String[] strArray= noLoginPaths.split(";");
			for(int i=0;i<strArray.length;i++){
				if(strArray[i]==null||"".equals(strArray[i])) 
					continue;
				if(request.getRequestURI().indexOf(strArray[i])!=-1){
					chain.doFilter(req, res);
					return;
				}
			}
		}
		
		

		if(session.getAttribute("user")!=null){
			chain.doFilter(req, res);
		}
		
			else{
				response.sendRedirect("/index");
			}
	}

	@Override
	public void destroy() {
		
		
	}

}

package com.sn.security;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @author geloin
 * @date 2012-3-27 下午2:29:35
 */
@Repository
public class SystemInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 后台session控制
		List<String> noFilters=new ArrayList<String>();
		//登录模块
		noFilters.add("login.html");
		noFilters.add("logout.html");
		noFilters.add("veriCode.html");
		//其他公用缓存模块
		noFilters.add("DictionaryData");
		
		String uri = request.getRequestURI();

		boolean beFilter = true; 
		for (String s : noFilters) {
			if (uri.indexOf(s) != -1) {
				beFilter = false;
				break; 
			} 
		}
		if (beFilter) { 
			//是否为商户前台管理界面登入
			boolean isBusiness = uri.indexOf("business") == -1 ? false : true;
			if (isBusiness) {
				Object obj = request.getSession().getAttribute("memberid");
				if (null == obj) {
					response.sendRedirect("/business/login.html");
					return false;
				}
			} else {
				Object obj = request.getSession().getAttribute("userid");
				if (null == obj) {
					response.sendRedirect("/system/login.html");
					return false;
				}
			}
			
		}

		return super.preHandle(request, response, handler);
	}

}

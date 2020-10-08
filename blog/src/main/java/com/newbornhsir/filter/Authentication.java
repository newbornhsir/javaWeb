package com.newbornhsir.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.newbornhsir.SpringApplication;
import com.newbornhsir.beans.UserSession;
import com.newbornhsir.util.ResultUtil;

@WebFilter(urlPatterns="/*")
public class Authentication implements Filter{
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		chain.doFilter(request, response);
		return;
		// 加载spring配置
//		SpringApplication.bootstrap();
//		request.setCharacterEncoding("UTF-8");
//		boolean isLogin = ((UserSession) SpringApplication.applicationContext.getBean("userSessionBean")).isLogin((HttpServletRequest) request);
//		if (isLogin) {
//			chain.doFilter(request, response);
//			return;
//		}
//		// 白名单中接口放行
//		List<String> whiteList = new ArrayList<>();
//		whiteList.add("/login");
//		whiteList.add("/register");
//		String path = ((HttpServletRequest) request).getRequestURI();
//		long exist = whiteList
//						.stream()
//						// 简单的验证
//						.filter(whiteItem -> path.contains(whiteItem))
//						.count();
//		if (exist > 0) {
//			chain.doFilter(request, response);
//			return;
//		}
//		/**
//		 * 返回数据，用户未登陆
//		 */
//		response.setContentType("application/json;charset=utf-8");
//		PrintWriter pw = response.getWriter();
//		pw.print(ResultUtil.response(ResultUtil.CodeEnum.NOT_LOGIN));
//		pw.flush();
//		pw.close();
	}

}

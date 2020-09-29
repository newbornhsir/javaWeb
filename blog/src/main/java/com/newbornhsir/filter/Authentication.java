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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.newbornhsir.util.RedisUtil;
import com.newbornhsir.util.ResponseResult;

import redis.clients.jedis.Jedis;

@WebFilter(urlPatterns="/*")
public class Authentication implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Cookie[] cookies = ((HttpServletRequest) request).getCookies();
		if (cookies != null) {
			for(Cookie cookie: cookies) {
				if (cookie.getName().equals("user")) {
					// 判断登陆状态
					String sessionId = cookie.getValue();
					System.out.println(sessionId);
					Jedis jedis = RedisUtil.connect();
					if (jedis.exists(sessionId)) {
						System.out.println("用户已经登陆，且未过期");
						jedis.expire(sessionId, 60*60*24); // 更新过期时间
						chain.doFilter(request, response);
						return;
					}
				}
			}
		}
		// 白名单中接口放行
		List<String> whiteList = new ArrayList<>();
		whiteList.add("/login");
		whiteList.add("/register");
		String path = ((HttpServletRequest) request).getRequestURI();
		long exist = whiteList
						.stream()
						// 简单的验证
						.filter(whiteItem -> path.contains(whiteItem))
						.count();
		if (exist > 0) {
			chain.doFilter(request, response);
			return;
		}
		/**
		 * 返回数据，用户未登陆
		 */
		String json = new ResponseResult<>(ResponseResult.Code.NotLogin).toJson();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.flush();
		pw.close();
	}

}

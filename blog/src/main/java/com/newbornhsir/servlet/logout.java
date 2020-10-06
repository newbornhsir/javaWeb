package com.newbornhsir.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newbornhsir.util.RedisUtil;
import com.newbornhsir.util.ResultUtil;

import redis.clients.jedis.Jedis;

@WebServlet(urlPatterns="/logout")
public class logout extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 获取cookie
		Cookie[] cookies = ((HttpServletRequest) req).getCookies();
		List<Cookie> sessionCookies = Arrays.asList(cookies).stream().filter((Cookie cookie) -> cookie.getName().equals("user")).collect(Collectors.toList());
		PrintWriter pw = resp.getWriter();
		if (sessionCookies.size() == 1) {
			// 携带session， 校验session是否过期
			String sessionId = sessionCookies.get(0).getValue();
			Jedis jedis = RedisUtil.connect();
			if (jedis.exists(sessionId)) {
				jedis.del(sessionId); // 删除
				pw.println(ResultUtil.success());
			} else {
				// 清除session
				Cookie userCookie = new Cookie("user", null);
				resp.addCookie(userCookie);
				pw.println(ResultUtil.response(ResultUtil.CodeEnum.NOT_LOGIN));
			}
		} else {
			pw.println(ResultUtil.response(ResultUtil.CodeEnum.NOT_LOGIN));
		}
		pw.close();
	}

}

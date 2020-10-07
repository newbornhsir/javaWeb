package com.newbornhsir.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newbornhsir.util.RedisUtil;
import com.newbornhsir.util.ResultUtil;

import redis.clients.jedis.Jedis;

@WebServlet(urlPatterns="/auth")
public class Auth extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Auth() {
		System.out.println("auth 实例化");
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json;charset=utf-8");
		// 获取session判断是否登陆
		Cookie[] cookies = ((HttpServletRequest) req).getCookies();
		List<Cookie> sessionCookies = Arrays.asList(cookies).stream().filter((Cookie cookie) -> cookie.getName().equals("user")).collect(Collectors.toList());
		PrintWriter pw = resp.getWriter();
		if (sessionCookies.size() == 1) {
			// 携带session， 校验session是否过期
			String sessionId = sessionCookies.get(0).getValue();
			Jedis jedis = RedisUtil.connect();
			if (jedis.exists(sessionId)) {
				System.out.println(sessionId);
				jedis.expire(sessionId, 60*60*24); // 更新过期时间
				Map<String, String> map = new HashMap<String, String>();
				String userName = URLDecoder.decode(sessionId, "UTF-8").split("%=%=%=")[1];
				map.put("userName", userName);
				pw.println(ResultUtil.success(map));
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

package com.newbornhsir.beans;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.newbornhsir.util.RedisUtil;

import redis.clients.jedis.Jedis;

public class UserSession {
	
	public UserSession() {
		System.out.println("userSession constructor");
	}
	
	public String getLoginSession(HttpServletRequest request) {
		String session = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for(Cookie cookie: cookies) {
				if (cookie.getName().equals("user")) {
					// 判断登陆状态
					session = cookie.getValue();
					System.out.println(session);
				}
			}
		}
		return session;
	}
	
	public boolean isLogin(HttpServletRequest request) {
		String sessionId = getLoginSession(request);
		boolean isLogin = false;
		if (sessionId != null) {
			Jedis jedis = RedisUtil.connect();
			if (jedis.exists(sessionId)) {
				System.out.println("用户已经登陆，且未过期");
				jedis.expire(sessionId, 60*60*24); // 更新过期时间
				isLogin = true;
				jedis.close();
			}
		}
		return isLogin;
	}
}

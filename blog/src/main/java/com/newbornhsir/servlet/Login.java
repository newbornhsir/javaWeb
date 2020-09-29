package com.newbornhsir.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import com.newbornhsir.util.MybatisConfig;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newbornhsir.dao.po.User;
import com.newbornhsir.util.MybatisConfig;
import com.newbornhsir.util.RedisUtil;
import com.newbornhsir.util.ResultUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

@WebServlet(urlPatterns="/login")
public class Login extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 获取请求参数
//		String queryString = req.getQueryString();
		// 获取提交的参数 , 表单提交的
//		String username = req.getParameter("username");
//		String password = req.getParameter("password");
		
		// 读取post提交的参数
		String jsonStr = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		System.out.println(jsonStr);
		JSONObject json = JSON.parseObject(jsonStr);
		String username = json.getString("username");
		String password = json.getString("password");
		String responseJson;
		if (username == null || password == null) {
			responseJson = ResultUtil.error(ResultUtil.CodeEnum.ACCOUNT_ERROE.getCode(), "请填写用户名或者密码");
		} else {
			SqlSession ss = MybatisConfig.sqlSessionFactory.openSession();
			List<User> users = ss.selectList("com.newbornhsir.dao.mapper.UserMapper.selectUserByUserName", username);
			if (users.size() == 0) {
				responseJson = ResultUtil.error(ResultUtil.CodeEnum.ACCOUNT_ERROE.getCode(), "用户名不存在");
			} else {
				User user = users.get(0);
				String pwd = user.getPwd();
				if (pwd != null && pwd.equals(password)) {
					/**
					 * session 存储
					 * 1. cookie存在sessionId, 删除
					 * 2. 创建
					 * 3. 设置cookie
					 */
					Jedis jedis = RedisUtil.connect();
					Cookie[] cookies = req.getCookies();
					System.out.println(cookies);
					String sessionId;
					try {
						for(Cookie cookie: cookies) {
							if (cookie.getName().equals("user")) {
								sessionId = cookie.getValue();
								jedis.del(sessionId);
							}
						}
					} catch(Exception e) {}
					
					HttpSession session = req.getSession();
					sessionId = session.getId();
					session.setAttribute("sessionId", sessionId);
					String userCookieValue = URLEncoder.encode(sessionId + "%=%=%=" + username, "UTF-8");
					Cookie userCookie = new Cookie("user", userCookieValue);
					userCookie.setPath("/");
					SetParams setParams = new SetParams();
					setParams.ex(24*60*60);
					jedis.set(userCookieValue, "", setParams);
					resp.addCookie(userCookie);
					responseJson = ResultUtil.success();
				
				} else {
					responseJson = ResultUtil.response(ResultUtil.CodeEnum.ACCOUNT_ERROE);
				}
			}
			ss.close();
		}
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.println(responseJson);
		pw.close();
	}
}

package com.newbornhsir.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import com.newbornhsir.util.MybatisConfig;

import org.apache.ibatis.session.SqlSession;

import com.newbornhsir.dao.po.User;
import com.newbornhsir.util.MybatisConfig;

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
		// 获取提交的参数
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		SqlSession ss = MybatisConfig.sqlSessionFactory.openSession();
		List<User> users = ss.selectList("com.newbornhsir.dao.mapper.UserMapper.selectUserByUserName", username);
		System.out.println(users);
		String message;
		if (users.size() == 0) {
			message = "用户名不存在";
		} else {
			User user = users.get(0);
			String pwd = user.getPwd();
			if (pwd != null && pwd.equals(password)) {
				// 设置session
				HttpSession session = req.getSession();
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				message = "登陆成功";
			} else {
				message = "用户名或密码错误";
			}
		}
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.println(message);
		ss.close();
		pw.close();
	}
}

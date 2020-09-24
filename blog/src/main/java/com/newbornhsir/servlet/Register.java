package com.newbornhsir.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.newbornhsir.dao.po.User;
import com.newbornhsir.util.MybatisConfig;

@WebServlet(urlPatterns="/register")
public class Register extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String username = req.getParameter("username");
		String pwd = req.getParameter("password");
		String age = req.getParameter("age");
		String address = req.getParameter("address");
		User user = new User();
		user.setName(username);
		user.setPwd(pwd);
		user.setAddress(address);
		try {
			user.setAge(Integer.valueOf(age));
		} catch (Exception e) {
			System.out.println(e);
		}
		// 先查后加， 用户名不能重复
		SqlSession ss = MybatisConfig.sqlSessionFactory.openSession();
		List<User> users = ss.selectList("com.newbornhsir.dao.mapper.UserMapper.selectUserByUserName", username);
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = res.getWriter();
		for(User u: users) {
			System.out.println(u);
		}
		if (users.size() > 0) {
			pw.println("<h1>用户名已存在 </h1>");
		} else {
			// insert
			int b = ss.insert("com.newbornhsir.dao.mapper.UserMapper.insert", user);
			ss.commit();
			System.out.println(b);
			String message;
			if (b > 0) {
				message = "<h1>注册成功 </h1>";
			} else {
				message = "<h1>创建失败 </h1>";
			}
			pw.println(message);
		}
		ss.close();
		pw.close();
	}
}

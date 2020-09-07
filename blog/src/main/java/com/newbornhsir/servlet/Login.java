package com.newbornhsir.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns="/login")
public class Login extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 获取请求参数
		String queryString = req.getQueryString();
		System.out.println(queryString);
		// 获取提交的参数
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println("username: " + username);
		System.out.println("password: " + password);
		// 参数存在则发送一个重定向, 这里会有空指针
		if(!(username.isEmpty() || password.isEmpty())) {
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			resp.sendRedirect("/blog");
		} else {
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter pw = resp.getWriter();
			pw.println("用户名或者密码错误");
			pw.close();
		}
	}
}

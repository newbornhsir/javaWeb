package com.newbornhsir.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newbornhsir.util.ResultUtil;

@WebServlet(urlPatterns="/auth")
public class Auth extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		Map<String, String> map = new HashMap<String, String>();
		map.put("test", "testvalue");
		pw.println(ResultUtil.success(map));
		pw.close();
	}
}

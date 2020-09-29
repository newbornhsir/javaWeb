package com.newbornhsir.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newbornhsir.util.ResponseResult;

@WebServlet(urlPatterns="/auth")
public class Auth extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ResponseResult<Object> responseResult = new ResponseResult<>(ResponseResult.Code.OK);
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.println(responseResult.toJson());
		pw.close();
	}
}

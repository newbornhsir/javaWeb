package com.newbornhsir.mvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/")
public class MVC extends HttpServlet{
	private Map<String, GetDispatcher> getMappings = new HashMap<String, GetDispatcher>();
	private Map<String, PostDispatcher> postMappings = new HashMap<String, PostDispatcher>();
	
	/**
	 * 1. 捕获所有的http请求后统一调度
	 * 2. 重命名index.jsp否则捕捉不到`/`路径
	 * 3. 调用注解配置的路径
	 * 4. 如何通过注解来自动获取dispatcher?
	 */
	private static final long serialVersionUID = 3874041595885145633L;

	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod().toLowerCase();
		String path = req.getRequestURI().substring(req.getContextPath().length());;
		PrintWriter pw = resp.getWriter();
		String result = "method: " + method + ";\r\npath: " + path;
		pw.println(result);
		// 获取包中所有的类
//		List<Class> classes = Thread.currentThread().getContextClassLoader();
		Class getMap = GetMapping.class;
		// 只对post和get做了处理
		if (method == "get") {
			GetDispatcher dispatcher = getMappings.get(path);
			System.out.println(dispatcher);
		} else {
			PostDispatcher dispatcher = postMappings.get("path");
			System.out.println(dispatcher);
		}
		pw.close();
	}
	
}

abstract class Dispatcher {
	private String path;
	private String method;
	protected abstract void invoke();
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
}

class GetDispatcher extends Dispatcher {
	public GetDispatcher() {
		super();
		this.setMethod("get");
	}

	@Override
	protected void invoke() {
		// TODO Auto-generated method stub
		
	}
}

class PostDispatcher extends Dispatcher {
	public PostDispatcher() {
		super();
		this.setMethod("post");
	}

	@Override
	protected void invoke() {
		// TODO Auto-generated method stub
		
	}
}

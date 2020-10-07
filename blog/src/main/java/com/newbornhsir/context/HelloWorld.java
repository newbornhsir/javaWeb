package com.newbornhsir.context;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class HelloWorld implements InitializingBean, DisposableBean {
	
	static {
		System.out.println("class is loaded");
	}
	
	public HelloWorld() {
		System.out.println("执行构造函数");
	}
	
	private String message;
	private CommonLog commonLog;
	private SpecialLog specialLog;
	
	// 构造函数注入
	public HelloWorld(CommonLog commonLog, SpecialLog specialLog) {
		this.commonLog = commonLog;
		this.specialLog = specialLog;
	}
	
	public void log1() {
		this.commonLog.log();
	}
	
	public void log2() {
		this.specialLog.log();
	}
	
	public String getMessage() {
		return message;
	}
	
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void destory() {
		System.out.println("销毁");
	}
	
	@Override
	public void afterPropertiesSet() {
		System.out.println("属性设置完成");
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("bean销毁");
	}
}

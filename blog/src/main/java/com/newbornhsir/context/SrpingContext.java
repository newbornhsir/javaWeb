package com.newbornhsir.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SrpingContext {
	static {
		// 初始化
		ApplicationContext context =  new ClassPathXmlApplicationContext("application.xml");
		System.out.println(context);
	}
	public static void main(String[] args) {
		
	}
}
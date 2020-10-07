package com.newbornhsir;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.newbornhsir.context.HelloWorld;

public class SpringApplication {
	static BootStrap singleton;
	public static ApplicationContext applicationContext;
	static class BootStrap {
		public void bootstrap() {
			// 加载配置
			System.out.println("创建Spring ApplicationContext");
			SpringApplication.applicationContext = new ClassPathXmlApplicationContext("Beans.xml");
		}
	}
	
	public static void bootstrap() {
		if (SpringApplication.singleton == null) {
			SpringApplication.singleton = new BootStrap();
			SpringApplication.singleton.bootstrap();
		}
	}
	public static void main(String[] args) {
		/*
		 * 创建上下文
		 * 读取配置文件中的bean，加载并实例化
		 */
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		System.out.println("准备上下文");
		HelloWorld hello = (HelloWorld) context.getBean("helloWorld");
		System.out.println(hello.getMessage());
		hello = (HelloWorld) context.getBean("helloWorld");
		System.out.println(hello.getMessage());
		hello.log1();
		hello.log2();
	}
}

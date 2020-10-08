package com.newbornhsir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newbornhsir.util.ResultUtil;

@Controller
public class Auth {
	static {
		System.out.println("controller 类加载");
	}
	@Autowired
	private Test test;
	
	public void setTest(Test test) {
		this.test = test;
	}
	
	@ResponseBody
	@RequestMapping(value="/auth", method=RequestMethod.GET)
	public String auth() {
		System.out.println("路径解析");
		test.test();
		return ResultUtil.response(ResultUtil.CodeEnum.NOT_LOGIN);
	}
}

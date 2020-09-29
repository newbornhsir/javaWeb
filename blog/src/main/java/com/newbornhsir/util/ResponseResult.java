package com.newbornhsir.util;

import com.alibaba.fastjson.JSON;

public class ResponseResult<T> {
	public static enum Code {
		OK(200, "成功"),
		NotLogin(201, "用户未登陆"),
		Common(400, ""),
		Error(500, "服务器错误");
		private int code;
		private String description;
		Code(int i, String string) {
			// TODO Auto-generated constructor stub
			code = i;
			description = string;
		}
		public int getCode() {
			return code;
		}
		public String getDescription() {
			return description;
		}
		
	};

	private int code;
	private T data;
	private String message;
	
	public ResponseResult() {
		this.code = Code.OK.getCode();
		this.message = Code.OK.getDescription();
	}
	
	public ResponseResult(T data) {
		this.code = Code.OK.getCode();
		this.message = Code.OK.getDescription();
		this.data = data;
	}
	
	public ResponseResult(Code code) {
		this.code = code.getCode();
		this.message = code.getDescription();
	}
	
	public ResponseResult(Code code, T data) {
		this.code = code.getCode();
		this.data = data;
		this.message = code.getDescription();
	}
	
	
	public int getCode() {
		return code;
	}
	public void setCode(Code code) {
		this.code = code.getCode();
		this.message = code.getDescription();
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toJson() {
		return JSON.toJSONString(this);
	}
}

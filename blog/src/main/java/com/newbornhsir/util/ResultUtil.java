package com.newbornhsir.util;

import com.alibaba.fastjson.JSON;

public class ResultUtil {
	// 成功的返回
	public static String success() {
		return ResultUtil.response(CodeEnum.SUCCESS);
	}
	// 带有响应数据的成功返回
	public static <T> String success(T data) {
		CodeEnum code = CodeEnum.SUCCESS;
		Result<T> result = new Result<T>(code.getCode(), code.getMessage());
		result.setData(data);
		return result.toString();
	}
	// 失败的返回
	public static String error(int code, String message) {
		Result<Object> result = new Result<>(code, message);
		return result.toString();
	}
	// 根据code设置响应
	public static String response(CodeEnum code) {
		Result<Object> result = new Result<>(code.getCode(), code.getMessage());
		return result.toString();
	}
	
	/**
	 * 
	 * @author henghongwei
	 *
	 * @param <T>
	 * json返沪
	 */
	public static class Result<T> {
		private String message;
		private int code;
		private T data;
		
		public Result() {}
		public Result(int code, String message) {
			this.code = code;
			this.message = message;
		}
		
		@Override
		public String toString() {
			return JSON.toJSONString(this);
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
	
	/**
	 * 
	 * @author henghongwei
	 *	响应码
	 */
	public static enum CodeEnum {
		SUCCESS(200, "操作成功"),
		ILLEGAL_ARGUMENT(201, "非法参数"),
		ACCOUNT_ERROE(202, "用户名或者密码错误"),
		NOT_LOGIN(401, "用户未登陆"),
		SERVER_ERROR(500, "操作成功");
		
		private Integer code;
		private String message;

		CodeEnum(int code, String message) {
			// TODO Auto-generated constructor stub
			this.code = code;
			this.message = message;
		}

		public Integer getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}
	}

}

package com.newbornhsir.orm;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@AbstractModal()
public class Test{
	private String name;
	public static void main(String[] args) {
		/**
		 * 通过反射加上注解读取到dbName
		 */
		Class<Test> cls = Test.class;
		AbstractModal[] abstractModals = cls.getAnnotationsByType(AbstractModal.class);
		if (abstractModals.length == 0) {
			return;
		}
		AbstractModal modalCls = abstractModals[0];
		String dbName = modalCls.value();
		if (dbName.isEmpty()) {
			dbName = cls.getSimpleName().toLowerCase();
		}
		System.out.println(dbName);
		/**
		 * 连接数据库， 判断table是否存在
		 * 1. 存在则不创建，并记录此条信息
		 * 2. 不存在，创建
		 */
		boolean exist = false;
		// 记录日志即已经创建过的表格
		List<String> logs = new ArrayList<String>();
		if (exist) {
			// 存在
			logs.add(dbName);
		} else {
			/**
			 * 1. 定义字段类型注解
			 * 2. 获取字段
			 * 3. 根据字段类型设置字段格式（chart, int, long....）
			 * 4. 拼装出创建表格的sql
			 * 5. 数据库连接，创建表格
			 */
			Field[] fields = cls.getDeclaredFields();
			// 获取到了字段，再继续处理
			System.out.println(fields);
		}
	}
}

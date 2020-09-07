package com.newbornhsir.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBase {
	static {
		try {
			// 加载jdbc驱动
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Throwable {
		System.out.println("123e");
		// TODO: 文件处理机制，这里使用绝对路径
		String jdbcUrl = "jdbc:sqlite:" + "/Users/henghongwei/Desktop/practise/java/java-web/test.db";
		// jdbc资源手动释放
		// Connection conn = DriverManager.getConnection(jdbcUrl);
		// conn.close();
		// 通过try可以实现自动释放资源
		try(Connection conn = DriverManager.getConnection(jdbcUrl)) {
			// 创建数据库
//			try(Statement statement = conn.createStatement()) {
//				String sql = "\n" + 
//						"create table user(\n" + 
//						"id int primary key,\n" + 
//						"name varchar(20),\n" + 
//						"age short,\n" + 
//						"address varchar(50),\n" + 
//						"salary decimal(10,3)\n" + 
//						");";
//				boolean res = statement.execute(sql);
//				System.out.println(res);
//			}
			
			// 增
//			try(Statement statement = conn.createStatement()) {
//				String sql = "insert into user (name, age, address, salary) values" +
//						"('小红', 18, 'china', 1000)";
//				boolean res = statement.execute(sql);
//				System.out.println(res);
//			}
			// 查询
//			try(Statement statement = conn.createStatement()) {
//				String sql = "select id, name from user";
//				try(ResultSet rs = statement.executeQuery(sql)) {
//					while (rs.next()) {
//						String output = "id: " + rs.getLong("id") + ",name: " + rs.getString("name");
//						System.out.println(output);
//					}
//				}
//			}
			// 修改
//try(Statement statement = conn.createStatement()) {
//	
//}
			// 查询
//try(Statement statement = conn.createStatement()) {
//	
//}
		}
	}
	
}

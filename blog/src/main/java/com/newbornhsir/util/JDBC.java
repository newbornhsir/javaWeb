package com.newbornhsir.util;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	static {
		try {
			// 加载jdbc驱动
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createTable(Connection conn) throws Exception {
		// 创建数据库
		try(Statement statement = conn.createStatement()) {
			String sql = "\n" + 
					"create table user(\n" + 
					"id integer primary key AUTOINCREMENT,\n" + 
					"name varchar(20),\n" + 
					"age short,\n" + 
					"address varchar(50),\n" +
					"pwd varchar(50)\n" +
					");";
			boolean res = statement.execute(sql);
			System.out.println(res);
		}
	}
	
	public static void dropTable(Connection conn) throws SQLException {
		Statement statement = conn.createStatement();
		String sql = "drop table user";
		statement.execute(sql);
	}
	
	
	public static void dynamicExecute(Method m) throws Exception {
		String jdbcUrl = "jdbc:sqlite:" + "/Users/henghongwei/Desktop/practise/java/java-web/test.db";
		try(Connection conn = DriverManager.getConnection(jdbcUrl)) {
			m.invoke(null, conn);
		}
	}
	
	public static void insert(Connection conn) throws SQLException {
		try(Statement statement = conn.createStatement()) {
			String sql = "insert into user (name, age, address, pwd) values" +
					"('小名', 18, 'china', '11')";
			boolean res = statement.execute(sql);
			System.out.println(res);
		}
	}
	
	public static void getUser(Connection conn) throws SQLException {
		try(Statement statement = conn.createStatement()) {
			String sql = "select id, name, pwd from user";
			try(ResultSet rs = statement.executeQuery(sql)) {
				while (rs.next()) {
					String output = "id: " + rs.getLong("id") + ",name: " + rs.getString("name")
					+ ", pwd: " + rs.getString("pwd");
					System.out.println(output);
				}
			}
		}
	}

	public static void main(String[] args) throws Throwable {
		// 反射来实现
		Method m = JDBC.class.getMethod("getUser", Connection.class);
		JDBC.dynamicExecute(m);
	}
	
}


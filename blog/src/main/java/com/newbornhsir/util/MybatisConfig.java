package com.newbornhsir.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.newbornhsir.dao.po.User;


public class MybatisConfig {
	public static SqlSessionFactory sqlSessionFactory;
	static {
		String resource = "mybatis.xml";
		InputStream inputStream = null;
		try {
			inputStream = (InputStream) Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MybatisConfig.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public static void main(String[] args) {
		SqlSession ss = MybatisConfig.sqlSessionFactory.openSession();
		List<User> users = ss.selectList("com.newbornhsir.dao.mapper.UserMapper.selectAllUser");
		for(User user: users) {
			System.out.println(user);
		}
	}
}

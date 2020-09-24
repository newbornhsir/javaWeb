package com.newbornhsir.dao.mapper;

import java.util.List;

import com.newbornhsir.dao.po.User;

public interface UserMapper {
	public void selectUserById(int id);
	public List<User> selectAllUser();
	public List<User> selectUserByUserName(String name);
	public Integer insert(User u);
}

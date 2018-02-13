package com.jhyarrow.myWeb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.jhyarrow.myWeb.domain.User;
import com.jhyarrow.myWeb.mapper.UserMapper;
import com.jhyarrow.myWeb.service.UserService;

public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	
	public User getUser(String username) {
		return userMapper.getUser(username);
	}

}

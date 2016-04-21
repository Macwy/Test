package com.daoyin.dao;

import java.util.List;
import java.util.Map;

import com.daoyin.bean.User;

public interface UserDao {
	
	public User getUser(Long id);
	
	public List<Map<String,Object>> findAll();
	
}

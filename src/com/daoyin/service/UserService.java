package com.daoyin.service;

import java.util.List;
import java.util.Map;

import com.daoyin.bean.User;

public interface UserService {

	public User getUser(Long id);
	
	public List<Map<String,Object>> findAll();
	
}

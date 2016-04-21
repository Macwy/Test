package com.daoyin.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daoyin.bean.User;
import com.daoyin.dao.UserDao;
import com.daoyin.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	
		@Override
		public User getUser(Long id) {
			return userDao.getUser(id);
		}
		
		
		public List<Map<String,Object>> findAll(){
			return userDao.findAll();
		}
	
}

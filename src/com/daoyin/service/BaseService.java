package com.daoyin.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.daoyin.util.OrderBy;


public interface BaseService<T> {

	void addEntity(T t);

	void delete(String where, Object... params);

	void update(T t);

	public Integer count(String where, Object... params);

	List<T> getEntityAll();

	List<T> getAllByProperty(String where, Object params);

	List<T> getAllByProperty(LinkedHashMap<String, OrderBy> orderBy,String
	where,Object params);
	List<T> getAllByProperty(Integer pageIndex, Integer pageSize, String where,
			Object params);

	 List<T> getAllByProperty(LinkedHashMap<String, OrderBy> orderBy,Integer
	pageIndex,Integer pageSize,String where,Object params);

	T getEntityByProperty(String propName, Object value);
	
}

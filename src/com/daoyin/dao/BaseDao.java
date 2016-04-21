package com.daoyin.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import com.daoyin.util.OrderBy;


public interface BaseDao<T>  {
	
	
	public void save(T t);

	public void update(T t);

	public T get(Serializable id);

	public T get(String where, Object... params);

	public void delete(String where, Object... params);

	public Integer count(String where, Object... params);

	public List<T> queryList();

	public List<T> queryList(String where, Object... params);

	public List<T> queryList(LinkedHashMap<String, OrderBy> orderBy,
			String where, Object... params);

	public List<T> queryList(Integer pageIndex, Integer pageSize, String where,
			Object... params);

	public List<T> queryList(Integer pageIndex, Integer pageSize,
			LinkedHashMap<String, OrderBy> orderBy, String where,
			Object... params);


}

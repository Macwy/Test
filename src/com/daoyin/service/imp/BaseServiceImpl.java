package com.daoyin.service.imp;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.daoyin.service.BaseService;
import com.daoyin.util.DaoUtil;
import com.daoyin.util.OrderBy;

@SuppressWarnings("unchecked")
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	private Class<T> entityClass = (Class<T>) (((ParameterizedType) (this
			.getClass().getGenericSuperclass())).getActualTypeArguments()[0]);
	
	@Resource
	private SessionFactory sessionFactory;

	protected Session getSession() {
		
		return sessionFactory.getCurrentSession();
	}

	public void save(T t) {
		getSession().save(t);
		
	}

	public void batchSave(List<T> ts) {
		for (T t : ts) {
			save(t);
		}
	}

	public void remove(Serializable id) {
		T t = get(id);
		if (t != null) {
			getSession().delete(t);
		}
	}

	public void batchRemove(List<Serializable> ids) {
		for (Serializable id : ids) {
			remove(id);
		}
	}

	
	public void update(T t) {
		getSession().update(t);
	}

	public void mereg(T t) {
		getSession().merge(t);
	}


	public T get(Serializable id) {
		return entityClass.cast(getSession().get(entityClass, id));
	}

	
	public T get(String where, Object... params) {
		String hql = "select distinct  o from " + entityClass.getSimpleName()
				+ " o" + (where == null ? "" : " where " + where);
	
		getSession().flush();
		getSession().clear();
		Query query = getSession().createQuery(hql);
		DaoUtil.buildParameter(query, where, params);
		return entityClass.cast(query.uniqueResult());
	}

	public <E> E getPropById(Class<E> type, String propName, String where,
			Object... params) {
		String hql = "select " + propName + " from "
				+ entityClass.getSimpleName() + " o"
				+ (where == null ? "" : " where " + where);
		Query query = getSession().createQuery(hql);
		DaoUtil.buildParameter(query, where, params);
		return type.cast(query.uniqueResult());
	}


	public Integer count(String where, Object... params) {
		String hql = "select count(o) from "
				+ entityClass.getSimpleName()
				+ " o"
				+ ((where == null || where.length() == 0) ? "" : " where "
						+ where);
		Query query = getSession().createQuery(hql);
		DaoUtil.buildParameter(query, where, params);
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

	public List<T> queryList() {
		return queryList(0, 0, null);
	}

	public List<T> queryList(String where, Object... params) {
		return queryList(0, 0, null, where, params);
	}

	public List<T> queryList(LinkedHashMap<String, OrderBy> orderBy) {
		return queryList(0, 0, orderBy);
	}

	public List<T> queryList(Integer pageIndex, Integer pageSize) {
		return queryList(pageIndex, pageSize, null);
	}

	public List<T> queryList(LinkedHashMap<String, OrderBy> orderBy,
			String where, Object... params) {
		return queryList(0, 0, orderBy, where, params);
	}

	public List<T> queryList(Integer pageIndex, Integer pageSize,
			LinkedHashMap<String, OrderBy> orderBy) {
		return queryList(pageIndex, pageSize, orderBy, null);
	}

	public List<T> queryList(Integer pageIndex, Integer pageSize, String where,
			Object... params) {
		return queryList(pageIndex, pageSize, null, where, params);
	}

	public List<T> queryList(Integer pageIndex, Integer pageSize,
			LinkedHashMap<String, OrderBy> orderBy, String where,
			Object... params) {
		String hql = "select o from "
				+ entityClass.getSimpleName()
				+ " o"
				+ ((where == null || where.length() == 0) ? "" : " where "
						+ where) + DaoUtil.buildOrderBy(orderBy);
		System.out.println(hql);
		Query query = getSession().createQuery(hql);
		DaoUtil.buildParameter(query, where, params);
		DaoUtil.buildPagination(query, pageIndex, pageSize);
		return query.list();
	}

	public List<T> getAllOrderBy() {
		String hql = "select o from " + entityClass.getSimpleName()
				+ " o order by o.orderby asc";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	public void delete(String where, Object... params) {
		String hql = "delete from "
				+ entityClass.getSimpleName()
				+ " o "
				+ ((where == null || where.length() == 0) ? "" : " where"
						+ where);
		Query query = getSession().createQuery(hql);
		DaoUtil.buildParameter(query, where, params);
		query.executeUpdate();
	}

}

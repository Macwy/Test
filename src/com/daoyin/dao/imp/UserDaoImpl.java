package com.daoyin.dao.imp;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.daoyin.bean.User;
import com.daoyin.dao.UserDao;

@Repository
public class UserDaoImpl extends BaseDaoImpl<UserDao> implements UserDao {
	
	@Override
	public User getUser(Long id) {
		String hql = "from User where id =:id";
		Query query = getSession().createQuery(hql);
		query.setLong("id",id);
		return (User)query.uniqueResult();
	}
	
	
	public List<Map<String,Object>> findAll(){
		String sql = "select * from user";
		Query query = getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
}

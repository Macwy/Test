package com.daoyin.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;



public final class DaoUtil {
	public static List<String> getParameterNames(String where) {
		List<String> results = new ArrayList<String>();
		if (where == null || where.length() == 0) {
			return results;
		}
		char c;
		int end;
		String paramName;
		for (int begin = 0; begin < where.length(); begin++) {
			c = where.charAt(begin);
			if (c == ':') {
				end = where.indexOf(' ', begin);
				end = end == -1 ? where.length() : end;
				if (end == begin + 1) {
					throw new RuntimeException("符号:后面不允许出现空格！");
				}
				paramName = where.substring(begin + 1, end);
				String regex = "^[a-zA-Z_$][a-zA-Z0-9_$]*$";
				if (!Pattern.matches(regex, paramName)) {
					throw new RuntimeException("【" + paramName + "】参数命名不规范！");
				}
				results.add(paramName);
			}
		}
		return results;
	}

	public static void buildParameter(Query query, String where,
			Object... params) {
		List<String> paramNames = getParameterNames(where);
		if (params != null) {
			if (paramNames.size() != params.length) {
				throw new RuntimeException("命名参数个数和实参个数不一致！");
			}
			for (int i = 0; i < paramNames.size(); i++) {
				query.setParameter(paramNames.get(i), params[i]);
			}
		}
	}

	public static void buildPagination(Query query, Integer pageIndex,
			Integer pageSize) {
		if (pageIndex != null && pageSize != null && pageIndex > 0
				&& pageSize > 0) {
			query.setFirstResult(pageSize * (pageIndex - 1));
			query.setMaxResults(pageSize);
		}
	}

	public static void buildPagination(Criteria criteria, Integer pageIndex,
			Integer pageSize) {
		if (pageIndex != null && pageSize != null && pageIndex > 0
				&& pageSize > 0) {
			criteria.setFirstResult(pageSize * (pageIndex - 1));
			criteria.setMaxResults(pageSize);
		}
	}

	public static String buildOrderBy(LinkedHashMap<String, OrderBy> orderBy) {
		StringBuilder builder = new StringBuilder();
		if (orderBy != null && orderBy.size() > 0) {
			builder.append(" order by ");
			for (Entry<String, OrderBy> kv : orderBy.entrySet()) {
				builder.append(kv.getKey()).append(" ").append(
						kv.getValue().getValue()).append(",");
			}
			builder.deleteCharAt(builder.length() - 1);
		}
		return builder.toString();
	}

	public static void buildOrderBy(Criteria criteria, Order... orders) {
		if (orders != null) {
			for (Order order : orders) {
				criteria.addOrder(order);
			}
		}
	}

	public static void buildCriterions(Criteria criteria,
			List<Criterion> criterions) {
		if (criterions != null) {
			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}
		}
	}
}

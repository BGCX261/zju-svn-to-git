package cn.itcast.bbs.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.itcast.bbs.dao.UserDao;
import cn.itcast.bbs.dao.base.GenericDaoImpl;
import cn.itcast.bbs.entities.QueryResult;
import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.privilege.Group;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:51:49 PM
 */
@SuppressWarnings("unchecked")
@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	public User findByLoginName(String loginName) {
		String hql = "from User u where u.loginName=?";
		return (User) findUniqueResult(hql, loginName);
	}

	public User findByLoginNameAndPassword(String loginName, String password) {
		String hql = "from User u where u.loginName=? and u.password=?";
		return (User) findUniqueResult(hql, new Object[] { loginName, password });
	}

	public User findByEmail(String email) {
		String hql = "from User u where u.email=?";
		return (User) findUniqueResult(hql, email);
	}

	public QueryResult<User> findByLoginNamePart(String loginNamePart, int firstResult, int maxResult) {
		String queryString = "select count(u) from User u where u.loginName like ?";
		int total = findIntResult(queryString, "%" + loginNamePart + "%");

		DetachedCriteria dc = DetachedCriteria.forClass(User.class)//
				.add(Restrictions.like("loginName", "%" + loginNamePart + "%"));
		List<User> items = getHibernateTemplate().findByCriteria(dc, firstResult, maxResult);

		return new QueryResult<User>(total, items);
	}

	public QueryResult<User> findByGroup(Group group, int firstResult, int maxResult) {
		String queryString = "select count(u) from User u where ? in elements(u.groups)";
		int total = findIntResult(queryString, group);

		DetachedCriteria dc = DetachedCriteria.forClass(User.class)//
				.createCriteria("groups")//
				.add(Restrictions.eq("id", group.getId()));
		List<User> items = getHibernateTemplate().findByCriteria(dc, firstResult, maxResult);

		return new QueryResult<User>(total, items);
	}

	// public Date getFirstRegisteredUserDate() {
	// // 数据库表中registerTime列的值有可能为 00:00:00 00:00:00
	// String hql = "select min(u.registerTime) from User where registerTime is not null and
	// registerTime>0";
	// return (Date) findUniqueResult(hql);
	// }

}

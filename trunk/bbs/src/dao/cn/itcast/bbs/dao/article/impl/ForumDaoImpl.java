package cn.itcast.bbs.dao.article.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.itcast.bbs.dao.article.ForumDao;
import cn.itcast.bbs.dao.base.GenericDaoImpl;
import cn.itcast.bbs.entities.article.Forum;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:51:49 PM
 */
@Repository("forumDao")
public class ForumDaoImpl extends GenericDaoImpl<Forum> implements ForumDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Forum> findAll() {
		String queryString = "from Forum f order by f.order";
		return getHibernateTemplate().find(queryString);
	}

	public Forum getNextByOrder(Forum forum) {
		DetachedCriteria dc = DetachedCriteria.forClass(Forum.class)//
				.add(Restrictions.eq("this.category", forum.getCategory()))//
				.add(Restrictions.gt("order", forum.getOrder()))//
				.addOrder(Order.asc("order"));
		return (Forum) findFirstResult(dc);
	}

	public Forum getPreviousByOrder(Forum forum) {
		DetachedCriteria dc = DetachedCriteria.forClass(Forum.class)//
				.add(Restrictions.eq("this.category", forum.getCategory()))//
				.add(Restrictions.lt("order", forum.getOrder()))//
				.addOrder(Order.desc("order"));
		return (Forum) findFirstResult(dc);
	}

}

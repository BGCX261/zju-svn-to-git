package cn.itcast.bbs.dao.article.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.itcast.bbs.dao.article.CategoryDao;
import cn.itcast.bbs.dao.base.GenericDaoImpl;
import cn.itcast.bbs.entities.article.Category;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:51:49 PM
 */
@SuppressWarnings("unchecked")
@Repository("categoryDao")
public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao {

	@Override
	public List<Category> findAll() {
		String hql = "from Category c order by c.order";
		return getHibernateTemplate().find(hql);
	}

	public Category getPreviousByOrder(Category category) {
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class)//
				.add(Restrictions.lt("order", category.getOrder()))//
				.addOrder(Order.desc("order"));
		return (Category) findFirstResult(dc);
	}

	public Category getNextByOrder(Category category) {
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class)//
				.add(Restrictions.gt("order", category.getOrder()))//
				.addOrder(Order.asc("order"));
		return (Category) findFirstResult(dc);
	}
}

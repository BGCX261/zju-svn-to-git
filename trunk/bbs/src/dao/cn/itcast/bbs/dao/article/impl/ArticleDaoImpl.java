package cn.itcast.bbs.dao.article.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.itcast.bbs.dao.article.ArticleDao;
import cn.itcast.bbs.dao.base.GenericDaoImpl;
import cn.itcast.bbs.entities.QueryResult;
import cn.itcast.bbs.entities.article.Article;

@Repository("articleDao")
@SuppressWarnings("unchecked")
public class ArticleDaoImpl extends GenericDaoImpl<Article> implements ArticleDao {

	public QueryResult<Article> findByPostTimeRange(Date start, Date end, int firstResult, int maxResult) {
		String queryString = "select count(a) from Article a where a.postTime between ? and ?";
		int total = findIntResult(queryString, new Object[] { start, end });

		DetachedCriteria dc = DetachedCriteria.forClass(Article.class)//
				.add(Restrictions.between("this.postTime", start, end));
		List<Article> items = getHibernateTemplate().findByCriteria(dc, firstResult, maxResult);

		return new QueryResult<Article>(total, items);
	}

}

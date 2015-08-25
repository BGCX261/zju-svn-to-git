package cn.itcast.bbs.dao.article.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.itcast.bbs.dao.article.TopicDao;
import cn.itcast.bbs.dao.base.GenericDaoImpl;
import cn.itcast.bbs.entities.QueryResult;
import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.article.Forum;
import cn.itcast.bbs.entities.article.Topic;
import cn.itcast.bbs.entities.article.Topic.TopicType;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:51:49 PM
 */
@Repository("topicDao")
@SuppressWarnings("unchecked")
public class TopicDaoImpl extends GenericDaoImpl<Topic> implements TopicDao {

	public QueryResult<Topic> findByForum(Forum forum, int firstResult, int maxResult) {
		String queryString = "select count(t) from Topic t where t.forum=?";
		int total = findIntResult(queryString, forum);

		DetachedCriteria dc = DetachedCriteria.forClass(Topic.class)//
				.add(Restrictions.eq("forum", forum))//
				.addOrder(Order.desc("type"))//
				.addOrder(Order.desc("lastArticlePostTime"));
		List<Topic> items = getHibernateTemplate().findByCriteria(dc, firstResult, maxResult);

		return new QueryResult<Topic>(total, items);
	}

	public QueryResult<Topic> findByAuthor(User author, int firstResult, int maxResult) {
		String queryString = "select count(t) from Topic t where t.author=?";
		int total = findIntResult(queryString, author);

		DetachedCriteria dc = DetachedCriteria.forClass(Topic.class)//
				.add(Restrictions.eq("user", author));
		List<Topic> items = getHibernateTemplate().findByCriteria(dc, firstResult, maxResult);

		return new QueryResult<Topic>(total, items);
	}

	public List<Topic> findRecommendations(int max) {
		DetachedCriteria dc = DetachedCriteria.forClass(Topic.class)//
				.add(Restrictions.eq("type", TopicType.RECOMMENDED))//
				.addOrder(Order.desc("postTime"));
		return getHibernateTemplate().findByCriteria(dc, 0, max);
	}

	public Topic findLastTopicByForum(Forum forum) {
		String queryString = "from Topic t where t.id = (select max(t2.postTime) from Topic t2 where t2.forum=?)";
		return (Topic) findUniqueResult(queryString, forum);
	}
}

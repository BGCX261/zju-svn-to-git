package cn.itcast.bbs.dao.article.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.itcast.bbs.dao.article.ReplyDao;
import cn.itcast.bbs.dao.base.GenericDaoImpl;
import cn.itcast.bbs.entities.QueryResult;
import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.article.Reply;
import cn.itcast.bbs.entities.article.Topic;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:51:49 PM
 */
@Repository("replyDao")
@SuppressWarnings("unchecked")
public class ReplyDaoImpl extends GenericDaoImpl<Reply> implements ReplyDao {

	public List<Reply> findByTopic(Topic topic) {
		String queryString = "select r from Reply r where r.topic=?";
		return getHibernateTemplate().find(queryString, topic);
	}

	public QueryResult<Reply> findByTopic(Topic topic, int firstResult, int maxResult) {
		String queryString = "select count(r) from Reply r where r.topic=?";
		int total = findIntResult(queryString, topic);

		DetachedCriteria dc = DetachedCriteria.forClass(Reply.class)//
				.add(Restrictions.eq("topic", topic))//
				.addOrder(Order.asc("postTime"));
		List<Reply> items = getHibernateTemplate().findByCriteria(dc, firstResult, maxResult);

		return new QueryResult<Reply>(total, items);
	}

	// FIXME 使用 DetachedCriteria , 因为要setFirstResult..
	public QueryResult<Reply> findByAuthor(User author, int firstResult, int maxResult) {
		String queryString = "select count(r) from Reply r where r.author=?";
		int total = findIntResult(queryString, author);

		DetachedCriteria dc = DetachedCriteria.forClass(Reply.class)//
				.add(Restrictions.eq("author", author))//
				.addOrder(Order.asc("postTime"));
		List<Reply> items = getHibernateTemplate().findByCriteria(dc, firstResult, maxResult);

		return new QueryResult<Reply>(total, items);
	}

	public Reply findLastReplyByTopic(Topic topic) {
		DetachedCriteria dc = DetachedCriteria.forClass(Reply.class)//
				.add(Restrictions.eq("topic", topic))//
				.addOrder(Order.asc("postTime"));
		return (Reply) findFirstResult(dc);
	}

	public int countPreviousReplies(Reply reply) {
		String queryString = "select count(r) from Reply r where r.topic=? and r.postTime<?";
		return findIntResult(queryString, new Object[] { reply.getTopic(), reply.getPostTime() });
	}

	// public List<Reply> getByIdRange(Integer startId, Integer endId) {
	// String hql = "from Post p where p.id >= ? and p.id <= ?";
	// return getHibernateTemplate().find(hql, new Object[] { startId, endId });
	// }

	// public List<Reply> getByPostTimeRange(Date startTime, Date endTime) {
	// String hql = "from Post p where p.postTime >= ? and p.postTime <= ?";
	// return getHibernateTemplate().find(hql, new Object[] { startTime, endTime });
	// }

}

package cn.itcast.bbs.dao.article.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.bbs.dao.article.VoteRecordDao;
import cn.itcast.bbs.dao.base.GenericDaoImpl;
import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.article.Vote;
import cn.itcast.bbs.entities.article.VoteRecord;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:51:49 PM
 */
@Repository("voteRecordDao")
public class VoteRecordDaoImpl extends GenericDaoImpl<VoteRecord> implements VoteRecordDao {

	public boolean isUserVoted(Vote vote, User user) {
		String hql = "from VoteRecord vr where vr.voteItem.vote=? and voter=?";
		return getHibernateTemplate()//
				.find(hql, new Object[] { vote, user })//
				.size() > 0;
	}

}

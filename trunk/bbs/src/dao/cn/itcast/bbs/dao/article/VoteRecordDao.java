package cn.itcast.bbs.dao.article;

import cn.itcast.bbs.dao.base.GenericDao;
import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.article.Vote;
import cn.itcast.bbs.entities.article.VoteRecord;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:49:51 PM
 */
public interface VoteRecordDao extends GenericDao<VoteRecord> {
	/**
	 * 
	 * @param vote
	 * @param user
	 * @return 用户是否已投票
	 */
	boolean isUserVoted(Vote vote, User user);
}

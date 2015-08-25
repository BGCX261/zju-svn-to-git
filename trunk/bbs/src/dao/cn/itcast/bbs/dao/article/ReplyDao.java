package cn.itcast.bbs.dao.article;

import java.util.List;

import cn.itcast.bbs.dao.base.GenericDao;
import cn.itcast.bbs.entities.QueryResult;
import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.article.Reply;
import cn.itcast.bbs.entities.article.Topic;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:49:36 PM
 */
public interface ReplyDao extends GenericDao<Reply> {

	/**
	 * 查询指定主题中的回复
	 * 
	 * @param topic
	 * @return
	 */
	List<Reply> findByTopic(Topic topic);

	/**
	 * 查询属于指定主题的回复文章
	 * 
	 * @param topic
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	QueryResult<Reply> findByTopic(Topic topic, int firstResult, int maxResult);

	/**
	 * 查询指定用户发表的文章
	 * 
	 * @param author
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	QueryResult<Reply> findByAuthor(User author, int firstResult, int maxResult);

	/**
	 * @return 当前拥有的文章的总数量
	 */
	// int getTotal();
	/**
	 * @param startId
	 *            开始id
	 * @param endId
	 *            结束id
	 * @return id在指定范围的文章
	 */
	// List<Reply> getByIdRange(Integer startId, Integer endId);
	/**
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return 发表时间在指定范围的文章
	 */
	// List<Reply> getByPostTimeRange(Date startTime, Date endTime);
	
	/**
	 * Count how many previous replies there are before the given reply id
	 * 
	 * @param reply
	 * @return
	 */
	 int countPreviousReplies(Reply reply);
	/**
	 * 
	 * @param topic
	 * @return 指定主题的最后发表的一篇文章
	 */
	Reply findLastReplyByTopic(Topic topic);

}

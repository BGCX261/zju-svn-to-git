package cn.itcast.bbs.dao.article;

import java.util.List;

import cn.itcast.bbs.dao.base.GenericDao;
import cn.itcast.bbs.entities.QueryResult;
import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.article.Forum;
import cn.itcast.bbs.entities.article.Topic;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:49:38 PM
 */
public interface TopicDao extends GenericDao<Topic> {

	/**
	 * 查询指定版面的中主题
	 * 
	 * @param forum
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	QueryResult<Topic> findByForum(Forum forum, int firstResult, int maxResult);

	/**
	 * 查询指定用户发表的主题
	 * 
	 * @param author
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	QueryResult<Topic> findByAuthor(User author, int firstResult, int maxResult);

	/**
	 * 查询推荐类型的主题, 最多max个
	 * 
	 * @param max
	 * @return 最多max个推荐主题
	 */
	List<Topic> findRecommendations(int max);

	/**
	 * 
	 * @param forum
	 * @return 指定版面的最后发表的主题
	 */
	Topic findLastTopicByForum(Forum forum);

}

package cn.itcast.bbs.service.article;

import java.util.List;

import cn.itcast.bbs.entities.PageInfo;
import cn.itcast.bbs.entities.article.Topic;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public interface TopicService {

	/**
	 * @param id
	 * @return 指定id的Topic
	 */
	Topic getTopic(int id);

	/**
	 * 获取Topic
	 * 
	 * @param ids
	 * @return
	 */
	List<Topic> getTopics(int[] ids);

	/**
	 * 查看次数加1
	 * 
	 * @param id
	 */
	void incrementViewCount(int id);

	/**
	 * @param forumId
	 * @param pageNum
	 * @return 指定页的数据
	 */
	PageInfo<Topic> findTopicsByForum(int forumId, int pageNum);

	/**
	 * 发表新主题<br>
	 * 
	 * @param topic
	 */
	void addNew(Topic topic);

	/**
	 * 更新主题信息<br>
	 * 不会更新投票和附件
	 * 
	 * @param topic
	 */
	void updateTopic(Topic topic);

	/**
	 * 查询(最多max个)推荐的主题
	 * 
	 * @param max
	 *            数量
	 * @return 最新推荐的(max个)主题
	 */
	List<Topic> findRecommendations(int max);

	/**
	 * 删除主题
	 * 
	 * @param ids
	 *            要删除的主题的id
	 * @param reason
	 */
	void deleteTopics(int[] ids, String reason);

	/**
	 * 移动主题
	 * 
	 * @param ids
	 *            要移动的主题的id
	 * @param destFourmId
	 *            目的版面的id
	 * @param reason
	 */
	void moveTopics(int[] ids, int destForumId, String reason);

	/**
	 * 锁定主题
	 * 
	 * @param ids
	 *            要锁定的主题的id
	 * @param reason
	 */
	void lockTopics(int[] ids, String reason);

	/**
	 * 主题解锁
	 * 
	 * @param ids
	 *            要解锁的主题的id
	 * @param reason
	 */
	void unlockTopics(int[] ids, String reason);

}

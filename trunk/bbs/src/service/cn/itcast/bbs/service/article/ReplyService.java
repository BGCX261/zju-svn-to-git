package cn.itcast.bbs.service.article;

import cn.itcast.bbs.entities.PageInfo;
import cn.itcast.bbs.entities.article.Reply;
import cn.itcast.bbs.exception.runtime.ItcastTopicLockedException;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public interface ReplyService {

	/**
	 * @param id
	 * @return 指定的Reply
	 */
	Reply getReply(int id);

	/**
	 * @param topicId
	 * @param pageNum
	 * @return 指定页的数据
	 */
	PageInfo<Reply> findRepliesByTopicId(int topicId, int pageNum);

	/**
	 * 发表回复
	 * 
	 * @param reply
	 * @throws ItcastTopicLockedException
	 *             如果回复的主题已被锁定，则抛此异常
	 */
	void addNew(Reply reply) throws ItcastTopicLockedException;

	/**
	 * 更新Reply, 不会更新附件.
	 * 
	 * @param reply
	 */
	void updateReply(Reply reply);

	/**
	 * @param id
	 * @return 在显示回复列表时，指定的Reply所在的页码
	 */
	int calculatePageNum(int id);

	/**
	 * 删除回复
	 * 
	 * @param ids
	 *            要删除的回复的id
	 * @param reason
	 */
	void deleteReplies(int[] ids, String reason);

}

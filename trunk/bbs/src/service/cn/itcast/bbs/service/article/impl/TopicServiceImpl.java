package cn.itcast.bbs.service.article.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bbs.ExecutionContext;
import cn.itcast.bbs.cfg.SystemGlobals;
import cn.itcast.bbs.entities.PageInfo;
import cn.itcast.bbs.entities.QueryResult;
import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.article.DeletedArticle;
import cn.itcast.bbs.entities.article.Forum;
import cn.itcast.bbs.entities.article.Reply;
import cn.itcast.bbs.entities.article.Topic;
import cn.itcast.bbs.entities.article.Topic.TopicStatus;
import cn.itcast.bbs.entities.article.Topic.TopicType;
import cn.itcast.bbs.entities.log.OperationLog;
import cn.itcast.bbs.entities.log.OperationLog.OperationLogType;
import cn.itcast.bbs.entities.search.SearchableArticle;
import cn.itcast.bbs.logic.helper.AttachmentHelper;
import cn.itcast.bbs.service.article.TopicService;
import cn.itcast.bbs.service.base.BaseService;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
@Service("topicService")
@Transactional(readOnly = true)
public class TopicServiceImpl extends BaseService implements TopicService {

	public Topic getTopic(int id) {
		return topicDao.get(id);
	}

	@Transactional
	public void incrementViewCount(int id) {
		Topic topic = topicDao.get(id);
		topic.setViewCount(topic.getViewCount() + 1);
		topicDao.update(topic);
	}

	public PageInfo<Topic> findTopicsByForum(int forumId, int pageNum) {
		Forum forum = forumDao.get(forumId);
		int pageSize = SystemGlobals.getSettings().getTopicsPerPage();
		int firstResult = PageInfo.calculateFirstResult(pageNum, pageSize);

		QueryResult<Topic> qr = topicDao.findByForum(forum, firstResult, pageSize);
		return PageInfo.populate(qr, pageNum, pageSize);
	}

	@Transactional
	public void addNew(Topic topic) {
		topic.setNeedModerate(false);
		topic.setType(TopicType.NORMAL);
		topic.setStatus(TopicStatus.NORMAL);

		topic.setLastArticlePostTime(topic.getPostTime());
		topicDao.save(topic);// Save topic

		// 更新相关信息
		Forum forum = topic.getForum();
		forum.setLastTopic(topic);
		forum.setTopicCount(forum.getTopicCount() + 1);
		forum.setArticleCount(forum.getArticleCount() + 1);
		forumDao.update(forum); // Update forum

		User author = topic.getAuthor();
		author.setTopicCount(author.getTopicCount() + 1);
		author.setArticleCount(author.getArticleCount() + 1);
		userDao.update(author); // Update user

		// FXIME 保存附件(如果有), 在哪里保存
		AttachmentHelper.storeAttachmentFiles(topic.getAttachments());

		// 建立索引
		articleIndexDao.create(new SearchableArticle(topic));
	}

	@Transactional
	public void updateTopic(Topic topic) {
		// FIXME 不记录文章编辑次数，因为管理员改变类型也会增加
		// topic.setEditCount(topic.getEditCount() + 1);
		// topic.setLastEditTime(new Date());
		topicDao.update(topic);

		// Update index
		articleIndexDao.save(new SearchableArticle(topic));
	}

	public List<Topic> findRecommendations(int max) {
		return topicDao.findRecommendations(max);
	}

	@Transactional
	public void deleteTopics(int[] ids, String reason) {
		List<Topic> topics = getTopicsByIds(ids);

		for (Topic topic : topics) {
			Forum forum = topic.getForum();

			forum.setLastTopic(null);
			// FIXME 放这里就抛异常？failed to lazily initialize a collection, no session or session was closed
			// 出错行为: AttachmentHelper.deleteAttachmentFiles(topic.getAttachments());
			// topicDao.delete(topic); // Delete topic

			DeletedArticle da = new DeletedArticle(topic);
			deletedArticleDao.save(da);

			// 主题中包含的文章的数量,为回复数加1,因为还有一个主帖.
			int totalArticlesInTopic = topic.getReplyCount() + 1;
			Topic lastTopicInForum = topicDao.findLastTopicByForum(forum);

			// 更新Forum的有关信息
			forum.setTopicCount(forum.getTopicCount() - 1);
			forum.setArticleCount(forum.getArticleCount() - totalArticlesInTopic);
			forum.setLastTopic(lastTopicInForum);
			forumDao.update(forum);

			// 更新作者的有关信息
			User author = topic.getAuthor();
			author.setTopicCount(author.getTopicCount() - 1);
			author.setArticleCount(author.getArticleCount() - totalArticlesInTopic);
			userDao.update(author);

			// // log FIXME 做日志
			OperationLog olog = new OperationLog();
			olog.setEntityId(topic.getId());
			olog.setEntityType(Topic.class);
			olog.setLogTime(new Date());
			olog.setOperator(ExecutionContext.get().getCurrentUser());
			olog.setOperIpAddr(ExecutionContext.get().getIpAddr());
			olog.setType(OperationLogType.DELETE_TOPIC);
			operationLogDao.save(olog);

			// 删除附件文件
			AttachmentHelper.deleteAttachmentFiles(topic.getAttachments());
			for (Reply r : topic.getReplies()) {
				AttachmentHelper.deleteAttachmentFiles(r.getAttachments());
			}

			
			// 应放上面
			topicDao.delete(topic); // Delete topic

			// 删除索引
			articleIndexDao.deleteByTopicId(topic.getId());
		}
	}

	@Transactional
	public void moveTopics(int[] ids, int destForumId, String reason) {
		Forum destForum = forumDao.get(destForumId);
		List<Topic> topics = getTopicsByIds(ids);

		for (Topic topic : topics) {
			Forum sourceForum = topic.getForum();
			topic.setForum(destForum);
			topicDao.update(topic);

			// 主题中包含的文章的数量,为回复数加1,因为还有一个主帖.
			int totalArticlesInTopic = topic.getReplyCount() + 1;

			sourceForum.setTopicCount(sourceForum.getTopicCount() - 1); // topic count
			sourceForum.setArticleCount(sourceForum.getArticleCount() - totalArticlesInTopic);
			sourceForum.setLastTopic(topicDao.findLastTopicByForum(sourceForum));
			forumDao.update(sourceForum);

			destForum.setTopicCount(destForum.getTopicCount() + 1); // topic count
			destForum.setArticleCount(destForum.getArticleCount() + totalArticlesInTopic);
			destForum.setLastTopic(topicDao.findLastTopicByForum(destForum));
			forumDao.update(destForum);

			// FIXME 做日志
			OperationLog olog = new OperationLog();
			olog.setComment(reason);
			olog.setEntityId(topic.getId());
			olog.setEntityType(Topic.class);
			olog.setLogTime(new Date());
			olog.setOperator(ExecutionContext.get().getCurrentUser());
			olog.setOperIpAddr(ExecutionContext.get().getIpAddr());
			olog.setType(OperationLogType.MOVE_TOPICS);
			operationLogDao.save(olog);

			// Update index
			articleIndexDao.updateAllArticles(topic);
		}
	}

	@Transactional
	public void lockTopics(int[] ids, String reason) {
		List<Topic> topics = getTopicsByIds(ids);

		for (Topic topic : topics) {
			topic.setStatus(TopicStatus.LOCKED);
			topicDao.update(topic);

			// FIXME 做日志
			OperationLog olog = new OperationLog();
			olog.setComment(reason);
			olog.setEntityId(topic.getId());
			olog.setEntityType(Topic.class);
			olog.setLogTime(new Date());
			olog.setOperator(ExecutionContext.get().getCurrentUser());
			olog.setOperIpAddr(ExecutionContext.get().getIpAddr());
			olog.setType(OperationLogType.LOCK_TOPIC);
			operationLogDao.save(olog);
		}
	}

	@Transactional
	public void unlockTopics(int[] ids, String reason) {
		List<Topic> topics = getTopicsByIds(ids);

		for (Topic topic : topics) {
			topic.setStatus(TopicStatus.NORMAL);
			topicDao.update(topic);

			// FIXME 做日志
			// FIXME 做日志
			OperationLog olog = new OperationLog();
			olog.setComment(reason);
			olog.setEntityId(topic.getId());
			olog.setEntityType(Topic.class);
			olog.setLogTime(new Date());
			olog.setOperator(ExecutionContext.get().getCurrentUser());
			olog.setOperIpAddr(ExecutionContext.get().getIpAddr());
			olog.setType(OperationLogType.UNLOCK_TOPIC);
			operationLogDao.save(olog);
		}
	}

	public List<Topic> getTopics(int[] ids) {
		return getTopicsByIds(ids);
	}

	/**
	 * 通过id获取主题的集合
	 * 
	 * @param ids
	 * @return
	 */
	protected List<Topic> getTopicsByIds(int[] ids) {
		List<Topic> topics = new ArrayList<Topic>();
		if (ids == null) {
			return topics;
		}

		for (int id : ids) {
			Topic topic = topicDao.get(id);
			if (topic != null) {
				topics.add(topic);
			}
		}
		return topics;
	}
}

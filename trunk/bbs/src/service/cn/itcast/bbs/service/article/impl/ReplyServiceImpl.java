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
import cn.itcast.bbs.entities.article.Forum;
import cn.itcast.bbs.entities.article.Reply;
import cn.itcast.bbs.entities.article.Topic;
import cn.itcast.bbs.entities.article.Topic.TopicStatus;
import cn.itcast.bbs.entities.log.OperationLog;
import cn.itcast.bbs.entities.log.OperationLog.OperationLogType;
import cn.itcast.bbs.entities.search.SearchableArticle;
import cn.itcast.bbs.exception.runtime.ItcastTopicLockedException;
import cn.itcast.bbs.logic.helper.AttachmentHelper;
import cn.itcast.bbs.service.article.ReplyService;
import cn.itcast.bbs.service.base.BaseService;

/**
 * @author 传智播客.汤阳光 Apr 9, 2008
 */
@Service("replyService")
@Transactional(readOnly = true)
public class ReplyServiceImpl extends BaseService implements ReplyService {

	public Reply getReply(int id) {
		return replyDao.get(id);
	}

	public PageInfo<Reply> findRepliesByTopicId(int topicId, int pageNum) {
		Topic topic = topicDao.get(topicId);
		int pageSize = SystemGlobals.getSettings().getRepliesPerPage();
		int firstResult = PageInfo.calculateFirstResult(pageNum, pageSize);

		QueryResult<Reply> qr = replyDao.findByTopic(topic, firstResult, pageSize);
		return PageInfo.populate(qr, pageNum, pageSize);
	}

	@Transactional
	public void addNew(Reply reply) throws ItcastTopicLockedException {
		Topic topic = reply.getTopic();
		if (TopicStatus.LOCKED.equals(topic.getStatus())) {
			throw new ItcastTopicLockedException("主题[id=" + topic.getId() + ",title=" + topic.getTitle() + "]已锁定,不能回复此主题");
		}

		// 设置楼层, 第一个回复是 1 楼, 计算时不能使用getReplyCount(), 因为可能为1楼，5楼，13楼，中间的都被删除了
		// TODO 这样写会导致, 如果最后一个回复被删除了，则发表的回复还是同一个楼层
		Reply lastReplyInTopic = topic.getLastReply();
		// 第一个回复是1楼,以后的回复楼层数递增
		int floor = (lastReplyInTopic == null) ? 1 : lastReplyInTopic.getFloor() + 1;
		reply.setFloor(floor);

		// 保存Post
		replyDao.save(reply);

		// 更新Topic相关信息
		topic.setLastReply(reply);
		topic.setReplyCount(topic.getReplyCount() + 1);
		topic.setLastArticlePostTime(reply.getPostTime());
		topicDao.update(topic);

		// 更新Forum相关信息
		Forum f = topic.getForum();
		f.setArticleCount(f.getArticleCount() + 1);
		forumDao.update(f);

		// 更新作者相关信息
		User author = reply.getAuthor();
		author.setArticleCount(author.getArticleCount() + 1);
		userDao.update(author);

		// FIXME 保存附件(如果有) ，发表新回复时不会带有附件，附件是单独添加的
		// if (p.getAttachments().size() > 0) {
		// AttachmentHelper.storeAttachments(p.getAttachments());
		// }

		try {
			Thread.sleep(500);
			System.out.println("------> 500");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 建立索引
		articleIndexDao.create(new SearchableArticle(reply));
	}

	@Transactional
	public void updateReply(Reply reply) {
		reply.setEditCount(reply.getEditCount() + 1);
		reply.setLastEditTime(new Date());
		replyDao.update(reply);
		articleIndexDao.save(new SearchableArticle(reply));// Update index
	}

	public int calculatePageNum(int id) {
		Reply reply = replyDao.get(id);

		// 这个文章之前有多少个回复文章
		int previousRepliesCount = replyDao.countPreviousReplies(reply);
		int pageSize = SystemGlobals.getSettings().getRepliesPerPage();

		// 总记录, 包含当前这个回复文章
		int totalItems = previousRepliesCount + 1;
		// 计算总计有多少页
		int totalPage = (totalItems + pageSize - 1) / pageSize;

		// 以上可以写成(totalItems + pageSize) / pageSize; 写成上面这样(加1再减1), 是为了更容易看明白.

		// 文章是最后一个, 所以在最后一页.
		return totalPage;
	}

	@Transactional
	public void deleteReplies(int[] ids, String reason) {
		List<Reply> replies = getReplies(ids);

		for (Reply reply : replies) {
			Topic topic = reply.getTopic();
			Forum forum = topic.getForum();

			topic.setLastReply(null);
			replyDao.delete(reply);

			// 更新Topic的相关信息
			Reply lastReplyInTopic = replyDao.findLastReplyByTopic(topic);
			// 如果删除的是最后一个回复, 则最后文章发表的时间就是主帖发表的时间了
			Date lastArticleInTopicPostTime = (lastReplyInTopic == null) ? topic.getPostTime() : lastReplyInTopic.getPostTime();

			topic.setLastReply(lastReplyInTopic);
			topic.setReplyCount(topic.getReplyCount() - 1);
			topic.setLastArticlePostTime(lastArticleInTopicPostTime);
			topicDao.update(topic);

			// Update forum's information
			forum.setArticleCount(forum.getArticleCount() - 1);
			forumDao.update(forum);

			// Update author's information
			User author = reply.getAuthor();
			author.setArticleCount(author.getArticleCount() - 1);
			userDao.update(author);

			// FIXME 做日志 log
			OperationLog olog = new OperationLog();
			olog.setComment(reason);
			olog.setEntityId(reply.getId());
			olog.setEntityType(Reply.class);
			olog.setLogTime(new Date());
			olog.setOperator(ExecutionContext.get().getCurrentUser());
			olog.setOperIpAddr(ExecutionContext.get().getIpAddr());
			olog.setType(OperationLogType.DELETE_REPLY);

			// FIXME delete attachments 应是只做删除标记?
			AttachmentHelper.deleteAttachmentFiles(reply.getAttachments());

			// Delete index
			articleIndexDao.delete(new SearchableArticle(reply));
		}
	}

	// ---

	/**
	 * 通过id获取回复的集合
	 * 
	 * @param ids
	 * @return
	 */
	protected List<Reply> getReplies(int[] ids) {
		List<Reply> replies = new ArrayList<Reply>();
		if (ids == null) {
			return replies;
		}

		for (int id : ids) {
			Reply post = replyDao.get(id);
			if (post != null) {
				replies.add(post);
			}
		}
		return replies;
	}
}

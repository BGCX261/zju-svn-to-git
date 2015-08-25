package cn.itcast.bbs.entities.article;

import java.util.Arrays;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.article.Topic.TopicStatus;
import cn.itcast.bbs.entities.article.Topic.TopicType;
import cn.itcast.bbs.exception.runtime.ItcastException;

public class DeletedArticle {

	private int id;
	private String title;// 标题
	private String content;// 内容
	private int editCount;// 编辑次数
	private Date lastEditTime;// 最后编辑时间

	private TopicType type;// 类型
	private TopicStatus status;// 状态(是否锁定)
	private User author;// 作者
	private Date postTime;// 发表时间
	private String ipAddr;// 发表文章时所用的IP地址
	private int viewCount;// 查看次数
	private int replyCount;// 回复数量

	private int articleId; // 原文章的id
	private String attachmentsIdStr;
	private String votesIdStr;// 包含的投票
	private String forumName;// 所属版面

	private Date deletedTime;
	private User deleter;

	public DeletedArticle() {
	}

	public DeletedArticle(Topic topic) {
		try {
			BeanUtils.copyProperties(this, topic);
		} catch (Exception e) {
			throw new ItcastException(e);
		}

		int[] attachmentsId = new int[topic.getAttachments().size()];
		int[] votesId = new int[topic.getVotes().size()];

		int index = 0;
		for (Attachment att : topic.getAttachments()) {
			attachmentsId[index++] = att.getId();
		}

		index = 0;
		for (Vote vote : topic.getVotes()) {
			votesId[index++] = vote.getId();
		}

		this.id = 0; // 不拷贝id
		this.articleId = topic.getId();
		this.forumName = topic.getForum().getName();
		this.attachmentsIdStr = Arrays.toString(attachmentsId);
		this.votesIdStr = Arrays.toString(votesId);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getEditCount() {
		return editCount;
	}

	public void setEditCount(int editCount) {
		this.editCount = editCount;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public TopicType getType() {
		return type;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public void setType(TopicType type) {
		this.type = type;
	}

	public TopicStatus getStatus() {
		return status;
	}

	public void setStatus(TopicStatus status) {
		this.status = status;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public String getForumName() {
		return forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	public String getAttachmentsIdStr() {
		return attachmentsIdStr;
	}

	public void setAttachmentsIdStr(String attachmentsIdStr) {
		this.attachmentsIdStr = attachmentsIdStr;
	}

	public String getVotesIdStr() {
		return votesIdStr;
	}

	public void setVotesIdStr(String votesIdStr) {
		this.votesIdStr = votesIdStr;
	}

	public Date getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(Date deletedTime) {
		this.deletedTime = deletedTime;
	}

	public User getDeleter() {
		return deleter;
	}

	public void setDeleter(User deleter) {
		this.deleter = deleter;
	}

}

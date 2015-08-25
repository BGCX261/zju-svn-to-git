package cn.itcast.bbs.web.struts.forms;

import cn.itcast.bbs.entities.article.Topic.TopicStatus;
import cn.itcast.bbs.entities.article.Topic.TopicType;
import cn.itcast.bbs.web.struts.forms.base.BaseActionForm;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
@SuppressWarnings("serial")
public class ModerateForm extends BaseActionForm {
	private TopicType topicType;
	private TopicStatus topicStatus;

	private int[] ids;
	private int forumId;
	private int destForumId;
	private String reason;

	private int topicId;

	public TopicType getTopicType() {
		return topicType;
	}

	public void setTopicType(TopicType topicType) {
		this.topicType = topicType;
	}

	public TopicStatus getTopicStatus() {
		return topicStatus;
	}

	public void setTopicStatus(TopicStatus topicStatus) {
		this.topicStatus = topicStatus;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public int getDestForumId() {
		return destForumId;
	}

	public void setDestForumId(int destForumId) {
		this.destForumId = destForumId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

}

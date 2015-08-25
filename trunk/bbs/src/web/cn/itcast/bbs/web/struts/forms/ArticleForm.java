package cn.itcast.bbs.web.struts.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cn.itcast.bbs.entities.article.Topic.TopicStatus;
import cn.itcast.bbs.entities.article.Topic.TopicType;
import cn.itcast.bbs.web.helper.WebArticleHelper;
import cn.itcast.bbs.web.struts.forms.base.BaseActionForm;

@SuppressWarnings("serial")
public class ArticleForm extends BaseActionForm {
	// --- Article
	private String content;

	// --- Topic
	private String title;
	private TopicType type = TopicType.NORMAL;
	private TopicStatus status = TopicStatus.NORMAL;
	private int forumId;

	// --- Reply
	private int topicId;

	public ActionErrors validateTopic(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if (StringUtils.isBlank(title)) {
			errors.add("title", new ActionMessage("请输入标题", false));
		} else if (title.length() > 255) {
			errors.add("title", new ActionMessage("标题太长, 最多允许255个字符", false));
		}
		if (WebArticleHelper.isHtmlContentEmpty(content)) {
			errors.add("content", new ActionMessage("请输入内容", false));
		} else if (content.length() > 65535) {
			errors.add("content", new ActionMessage("内容太长, 最多允许65535个字符", false));
		}
		return errors;
	}

	public ActionErrors validateReply(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if (WebArticleHelper.isHtmlContentEmpty(content)) {
			errors.add("content", new ActionMessage("请输入内容", false));
		} else if (content.length() > 65535) {
			errors.add("content", new ActionMessage("内容太长, 最多允许65535个字符", false));
		}
		return errors;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TopicType getType() {
		return type;
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

package cn.itcast.bbs.entities.search;

import java.util.Date;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

import cn.itcast.bbs.entities.article.Reply;
import cn.itcast.bbs.entities.article.Topic;

/**
 * @author 传智播客.汤阳光 Jul 7, 2008
 */
@Searchable
public class SearchableArticle {
	// 主键,格式为Topic或Reply的id值前加一个'Topic'或'Reply', 因为Topic和Reply的主键值会有相同的.
	private String sid; // String类型的标识

	// 文章基本信息
	private String title; // 文章的标题,因为只有Topic有标题,所以Reply的此项为null.
	private String content;
	private Date postTime;

	// 文章所属的主题的信息
	private int topicId;
	private String topicTitle; // 文章所属的主题的标题,只用于显示,不进行搜索.

	// 文章的作者信息
	private int authorId;
	private String authorLoginName;
	private String authorNickname;

	// 文章所属的版面
	private String forumName;

	public SearchableArticle() {}

	public SearchableArticle(Topic topic) {
		this.sid = "Topic" + topic.getId();
		this.title = topic.getTitle();
		this.content = topic.getContent();
		this.postTime = topic.getPostTime();
		this.forumName = topic.getForum().getName();//
		this.topicId = topic.getId();
		this.topicTitle = topic.getTitle();//
		this.authorId = topic.getAuthor().getId();
		this.authorLoginName = topic.getAuthor().getLoginName();
		this.authorNickname = topic.getAuthor().getNickname();
	}

	public SearchableArticle(Reply reply) {
		Topic topic = reply.getTopic();
		this.sid = "Reply" + reply.getId();
		this.title = null; // 回复没有标题
		this.content = reply.getContent();
		this.postTime = reply.getPostTime();
		this.forumName = topic.getForum().getName(); //
		this.topicId = topic.getId();
		this.topicTitle = topic.getTitle();//
		this.authorId = reply.getAuthor().getId();
		this.authorLoginName = reply.getAuthor().getLoginName();
		this.authorNickname = reply.getAuthor().getNickname();
	}

	@SearchableId
	public String getSid() {
		return sid;
	}

	@SearchableProperty(store = Store.YES, index = Index.ANALYZED, boost = 3f)
	public String getTitle() {
		return title;
	}

	@SearchableProperty(store = Store.YES, index = Index.ANALYZED)
	public String getContent() {
		return content;
	}

	@SearchableProperty(store = Store.YES, index = Index.NOT_ANALYZED)
	public Date getPostTime() {
		return postTime;
	}

	@SearchableProperty(store = Store.YES, index = Index.NO)
	public int getTopicId() {
		return topicId;
	}

	@SearchableProperty(store = Store.YES, index = Index.NO)
	public String getTopicTitle() {
		return topicTitle;
	}

	@SearchableProperty(store = Store.YES, index = Index.NO)
	public int getAuthorId() {
		return authorId;
	}

	@SearchableProperty(store = Store.YES, index = Index.NO)
	public String getAuthorLoginName() {
		return authorLoginName;
	}

	@SearchableProperty(store = Store.YES, index = Index.NOT_ANALYZED)
	public String getAuthorNickname() {
		return authorNickname;
	}

	@SearchableProperty(store = Store.YES, index = Index.NOT_ANALYZED)
	public String getForumName() {
		return forumName;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public void setAuthorLoginName(String authorLoginName) {
		this.authorLoginName = authorLoginName;
	}

	public void setAuthorNickname(String authorNickname) {
		this.authorNickname = authorNickname;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}
}

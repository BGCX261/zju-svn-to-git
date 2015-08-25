package cn.itcast.bbs.entities.article;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 主题
 * 
 * @author 传智博客.汤阳光 Jun 19, 2008
 */
public class Topic extends Article {

	/**
	 * 主题的状态
	 * 
	 * @author 传智播客.汤阳光 Apr 25, 2009
	 */
	public static enum TopicStatus {
		NORMAL("正常"),

		LOCKED("锁定");

		private final String label;

		private TopicStatus(String label) {
			this.label = label;
		}

		/** 显示的名称 */
		public String getLabel() {
			return this.label;
		}

		/** 所代表的值 */
		public String getValue() {
			return this.name();
		};
	}

	/**
	 * 主题的类型<br>
	 * 显示主题时将会按照类型的ordinal进行排序(升序)
	 * 
	 * @author 传智播客.汤阳光 Apr 25, 2009
	 */
	public static enum TopicType {
		// FIXME 精华帖, 精华帖与置顶帖的区别是什么.

		ANNOUNCE("公告"),

		STICKY("置顶"),

		RECOMMENDED("推荐"),

		NORMAL("正常");

		private final String label;

		private TopicType(String label) {
			this.label = label;
		}

		/** 显示的名称 */
		public String getLabel() {
			return this.label;
		}

		/** 所代表的值 */
		public String getValue() {
			return this.name();
		};
	}

	private String title;// 标题
	private TopicType type = TopicType.NORMAL;// 类型
	private TopicStatus status = TopicStatus.NORMAL;// 状态(是否锁定)
	private int viewCount;// 查看次数
	private int replyCount;// 回复数量
	private Reply lastReply;// 最后回复
	private Forum forum;// 所属版面
	private Date lastArticlePostTime;// 本主题中的最后一个文章发表的时间, 用于显示文章列表时的排序

	private List<Vote> votes = new ArrayList<Vote>(0);// 包含的投票
	private Set<Reply> replies = new HashSet<Reply>(0);

	public Topic() {}

	public TopicStatus getStatus() {
		return status;
	}

	public Forum getForum() {
		return forum;
	}

	public Reply getLastReply() {
		return lastReply;
	}

	public int getViewCount() {
		return viewCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public String getTitle() {
		return title;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public TopicType getType() {
		return type;
	}

	public void setType(TopicType type) {
		this.type = type;
	}

	public void setStatus(TopicStatus status) {
		this.status = status;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public void setLastReply(Reply lastReply) {
		this.lastReply = lastReply;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public Date getLastArticlePostTime() {
		return lastArticlePostTime;
	}

	public void setLastArticlePostTime(Date lastArticlePostTime) {
		this.lastArticlePostTime = lastArticlePostTime;
	}

	public Set<Reply> getReplies() {
		return replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("[Topic: ")//
				.append("id=").append(getId())//
				.append(",author=").append(getAuthor() == null ? null : getAuthor().getLoginName())//
				.append(",postTime=").append(getPostTime())//
				.append(",ipAddr=").append(getIpAddr())//
				.append(",type=").append(type)//
				.append(",status=").append(status)//
				.append(",forum=").append(forum == null ? null : forum.getName())//
				.append(",title=").append(title)//
				.append(",content=").append(getContent())//
				.append("]")//
				.toString();
	}
}

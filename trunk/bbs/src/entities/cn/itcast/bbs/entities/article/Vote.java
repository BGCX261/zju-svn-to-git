package cn.itcast.bbs.entities.article;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 投票
 * 
 * @author 传智博客.汤阳光 Jun 19, 2008
 */
public class Vote {

	private int id;
	private String title;// 投票标题
	private List<VoteItem> voteItems = new ArrayList<VoteItem>(0);// 包含的投票项
	private Date creationTime;// 开始时间
	private Date closeTime;// 结束时间
	private boolean multiple = false; // 是否是多选的,默认为单选
	private int result;// 总投票数(投票结果)
	private Topic topic;// 所属主题

	// private boolean canEdit;// FIXME 是否能编辑投票(不对应数据库表中的列)

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public List<VoteItem> getVoteItems() {
		return voteItems;
	}

	public Topic getTopic() {
		return topic;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public int getResult() {
		return result;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setVoteItems(List<VoteItem> voteItems) {
		this.voteItems = voteItems;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("[Vote: ")//
				.append("id=").append(id)//
				.append(",title=").append(title)//
				.append(",creationTime=").append(creationTime)//
				.append(",closeTime=").append(closeTime)//
				.append(",multiple=").append(multiple)//
				.append(",result=").append(result)//
				.append("]")//
				.toString();
	}
}

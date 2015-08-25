package cn.itcast.bbs.entities.article;

import java.util.HashSet;
import java.util.Set;

/**
 * 投票项
 * 
 * @author 传智博客.汤阳光 Jun 19, 2008
 */
public class VoteItem {

	private int id;
	private String content; // 此项的显示的标题
	private int result;// 投此项的票数
	private Set<VoteRecord> voteRecords = new HashSet<VoteRecord>(0); // 所对应的投票记录
	private Vote vote;// 所属的投票

	public VoteItem() {}

	public VoteItem(String content) {
		super();
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public Vote getVote() {
		return vote;
	}

	public int getResult() {
		return result;
	}

	public Set<VoteRecord> getVoteRecords() {
		return voteRecords;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public void setVoteRecords(Set<VoteRecord> voteRecords) {
		this.voteRecords = voteRecords;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final VoteItem other = (VoteItem) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("[VoteItem: ")//
				.append("id=").append(id)//
				.append(",content=").append(content)//
				.append(",vote=").append(vote == null ? null : vote.getTitle())//
				.append(",result=").append(result)//
				.append("]")//
				.toString();
	}
}

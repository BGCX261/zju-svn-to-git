package cn.itcast.bbs.entities.article;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import cn.itcast.bbs.entities.User;

/**
 * 投票记录
 * 
 * @author 传智博客.汤阳光 Jun 19, 2008
 */
public class VoteRecord {

	private int id;
	private User voter;// 投票人
	private String ipAddr;// 投票时所使用的IP地址
	private Date voteTime;// 投票时间
	private Set<VoteItem> voteItems = new HashSet<VoteItem>();// 所投的项目

	public int getId() {
		return id;
	}

	public User getVoter() {
		return voter;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public Date getVoteTime() {
		return voteTime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setVoter(User voter) {
		this.voter = voter;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public void setVoteTime(Date voteTime) {
		this.voteTime = voteTime;
	}

	public Set<VoteItem> getVoteItems() {
		return voteItems;
	}

	public void setVoteItems(Set<VoteItem> voteItems) {
		this.voteItems = voteItems;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("[VoteRecord: ")//
				.append("id=").append(id)//
				.append(",voter=").append(voter == null ? null : voter.getLoginName())//
				.append(",ipAddr=").append(ipAddr)//
				.append(",voteTime=").append(voteTime)//
				.append("]")//
				.toString();
	}
}

package cn.itcast.bbs.entities.article;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.itcast.bbs.entities.User;

/**
 * 文章
 * 
 * @author 传智播客.汤阳光 Apr 25, 2009
 * 
 */
public class Article {

	private int id;
	private String content;// 内容
	private User author;// 作者
	private Date postTime;// 发表时间
	private String ipAddr;// 发表文章时所用的IP地址
	private List<Attachment> attachments = new ArrayList<Attachment>(0);
	private boolean needModerate;// 是否需要审核
	private int editCount;// 编辑次数
	private Date lastEditTime;// 最后编辑时间
	// private boolean canEdit;// FIXME 是否能编辑(不对应数据库表中的列)

	public Article() {}

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public User getAuthor() {
		return author;
	}

	public Date getPostTime() {
		return postTime;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public boolean isNeedModerate() {
		return needModerate;
	}

	public int getEditCount() {
		return editCount;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public void setNeedModerate(boolean needModerate) {
		this.needModerate = needModerate;
	}

	public void setEditCount(int editCount) {
		this.editCount = editCount;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("[Article: ")//
				.append("id=").append(id)//
				.append(",author=").append(author == null ? null : author.getLoginName())//
				.append(",postTime=").append(postTime)//
				.append(",ipAddr=").append(ipAddr)//
				.append(",content=").append(content)//
				.append("]")//
				.toString();
	}
}

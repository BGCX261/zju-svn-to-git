package com.jsict.jszju.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * ItcastTopic entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_topic", uniqueConstraints = @UniqueConstraint(columnNames = "lastReplyId_"))
public class ItcastTopic implements java.io.Serializable {

	// Fields

	private Integer id;
	private ItcastForum itcastForum;
	private ItcastArticle itcastArticle;
	private ItcastReply itcastReply;
	private String title;
	private String typeName;
	private String statusName;
	private Integer viewCount;
	private Integer replyCount;
	private Date lastArticlePostTime;
	private Set<ItcastReply> itcastReplies = new HashSet<ItcastReply>(0);
	private Set<ItcastForum> itcastForums = new HashSet<ItcastForum>(0);
	private Set<ItcastVote> itcastVotes = new HashSet<ItcastVote>(0);

	// Constructors

	/** default constructor */
	public ItcastTopic() {
	}

	/** minimal constructor */
	public ItcastTopic(ItcastArticle itcastArticle) {
		this.itcastArticle = itcastArticle;
	}

	/** full constructor */
	public ItcastTopic(ItcastForum itcastForum, ItcastArticle itcastArticle,
			ItcastReply itcastReply, String title, String typeName,
			String statusName, Integer viewCount, Integer replyCount,
			Date lastArticlePostTime, Set<ItcastReply> itcastReplies,
			Set<ItcastForum> itcastForums, Set<ItcastVote> itcastVotes) {
		this.itcastForum = itcastForum;
		this.itcastArticle = itcastArticle;
		this.itcastReply = itcastReply;
		this.title = title;
		this.typeName = typeName;
		this.statusName = statusName;
		this.viewCount = viewCount;
		this.replyCount = replyCount;
		this.lastArticlePostTime = lastArticlePostTime;
		this.itcastReplies = itcastReplies;
		this.itcastForums = itcastForums;
		this.itcastVotes = itcastVotes;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id_", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "forumId_")
	public ItcastForum getItcastForum() {
		return this.itcastForum;
	}

	public void setItcastForum(ItcastForum itcastForum) {
		this.itcastForum = itcastForum;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_", unique = true, nullable = false, insertable = false, updatable = false)
	public ItcastArticle getItcastArticle() {
		return this.itcastArticle;
	}

	public void setItcastArticle(ItcastArticle itcastArticle) {
		this.itcastArticle = itcastArticle;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lastReplyId_", unique = true)
	public ItcastReply getItcastReply() {
		return this.itcastReply;
	}

	public void setItcastReply(ItcastReply itcastReply) {
		this.itcastReply = itcastReply;
	}

	@Column(name = "title_")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "typeName_")
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "statusName_")
	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Column(name = "viewCount_")
	public Integer getViewCount() {
		return this.viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	@Column(name = "replyCount_")
	public Integer getReplyCount() {
		return this.replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastArticlePostTime_", length = 19)
	public Date getLastArticlePostTime() {
		return this.lastArticlePostTime;
	}

	public void setLastArticlePostTime(Date lastArticlePostTime) {
		this.lastArticlePostTime = lastArticlePostTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastTopic")
	public Set<ItcastReply> getItcastReplies() {
		return this.itcastReplies;
	}

	public void setItcastReplies(Set<ItcastReply> itcastReplies) {
		this.itcastReplies = itcastReplies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastTopic")
	public Set<ItcastForum> getItcastForums() {
		return this.itcastForums;
	}

	public void setItcastForums(Set<ItcastForum> itcastForums) {
		this.itcastForums = itcastForums;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastTopic")
	public Set<ItcastVote> getItcastVotes() {
		return this.itcastVotes;
	}

	public void setItcastVotes(Set<ItcastVote> itcastVotes) {
		this.itcastVotes = itcastVotes;
	}

}
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

/**
 * ItcastArticle entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "itcast_article")
public class ItcastArticle implements java.io.Serializable {

	// Fields

	private Integer id;
	private ItcastUser itcastUser;
	private String content;
	private Date postTime;
	private String ipAddr;
	private String needModerate;
	private Integer editCount;
	private Date lastEditTime;
	private Set<ItcastTopic> itcastTopics = new HashSet<ItcastTopic>(0);
	private Set<ItcastReply> itcastReplies = new HashSet<ItcastReply>(0);
	private Set<ItcastAttachment> itcastAttachments = new HashSet<ItcastAttachment>(
			0);

	// Constructors

	/** default constructor */
	public ItcastArticle() {
	}

	/** full constructor */
	public ItcastArticle(ItcastUser itcastUser, String content, Date postTime,
			String ipAddr, String needModerate, Integer editCount,
			Date lastEditTime, Set<ItcastTopic> itcastTopics,
			Set<ItcastReply> itcastReplies,
			Set<ItcastAttachment> itcastAttachments) {
		this.itcastUser = itcastUser;
		this.content = content;
		this.postTime = postTime;
		this.ipAddr = ipAddr;
		this.needModerate = needModerate;
		this.editCount = editCount;
		this.lastEditTime = lastEditTime;
		this.itcastTopics = itcastTopics;
		this.itcastReplies = itcastReplies;
		this.itcastAttachments = itcastAttachments;
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
	@JoinColumn(name = "authorId_")
	public ItcastUser getItcastUser() {
		return this.itcastUser;
	}

	public void setItcastUser(ItcastUser itcastUser) {
		this.itcastUser = itcastUser;
	}

	@Column(name = "content_", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "postTime_", length = 19)
	public Date getPostTime() {
		return this.postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	@Column(name = "ipAddr_", length = 16)
	public String getIpAddr() {
		return this.ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	@Column(name = "needModerate_")
	public String getNeedModerate() {
		return this.needModerate;
	}

	public void setNeedModerate(String needModerate) {
		this.needModerate = needModerate;
	}

	@Column(name = "editCount_")
	public Integer getEditCount() {
		return this.editCount;
	}

	public void setEditCount(Integer editCount) {
		this.editCount = editCount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastEditTime_", length = 19)
	public Date getLastEditTime() {
		return this.lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastArticle")
	public Set<ItcastTopic> getItcastTopics() {
		return this.itcastTopics;
	}

	public void setItcastTopics(Set<ItcastTopic> itcastTopics) {
		this.itcastTopics = itcastTopics;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastArticle")
	public Set<ItcastReply> getItcastReplies() {
		return this.itcastReplies;
	}

	public void setItcastReplies(Set<ItcastReply> itcastReplies) {
		this.itcastReplies = itcastReplies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "itcastArticle")
	public Set<ItcastAttachment> getItcastAttachments() {
		return this.itcastAttachments;
	}

	public void setItcastAttachments(Set<ItcastAttachment> itcastAttachments) {
		this.itcastAttachments = itcastAttachments;
	}

}
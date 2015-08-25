package com.jsict.jszju.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Comment entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "comment")
public class Comment implements java.io.Serializable {

	// Fields

	private Integer id;
	private String commentcontent;
	private String articleId;
	private String userName;
	private String syndicResult;
	private String syndic;
	private String commentTime;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** minimal constructor */
	public Comment(String commentcontent, String articleId, String userName) {
		this.commentcontent = commentcontent;
		this.articleId = articleId;
		this.userName = userName;
	}

	/** full constructor */
	public Comment(String commentcontent, String articleId, String userName,
			String syndicResult, String syndic, String commentTime) {
		this.commentcontent = commentcontent;
		this.articleId = articleId;
		this.userName = userName;
		this.syndicResult = syndicResult;
		this.syndic = syndic;
		this.commentTime = commentTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "commentcontent", nullable = false, length = 65535)
	public String getCommentcontent() {
		return this.commentcontent;
	}

	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}

	@Column(name = "articleId", nullable = false, length = 20)
	public String getArticleId() {
		return this.articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	@Column(name = "userName", nullable = false, length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "syndicResult", length = 6)
	public String getSyndicResult() {
		return this.syndicResult;
	}

	public void setSyndicResult(String syndicResult) {
		this.syndicResult = syndicResult;
	}

	@Column(name = "syndic", length = 12)
	public String getSyndic() {
		return this.syndic;
	}

	public void setSyndic(String syndic) {
		this.syndic = syndic;
	}

	@Column(name = "commentTime", length = 20)
	public String getCommentTime() {
		return this.commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

}
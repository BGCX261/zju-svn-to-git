package com.jsict.jszju;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Article entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "article", catalog = "cedatabase")
public class Article implements java.io.Serializable {

	// Fields

	private Integer id;
	private String articleId;
	private String title;
	private String attribute;
	private String inputData;
	private String inputAdmin;
	private String isView;
	private String status;
	private String content;
	private String channelId;
	private String author;
	private String visitTime;

	// Constructors

	/** default constructor */
	public Article() {
	}

	/** minimal constructor */
	public Article(String title, String attribute) {
		this.title = title;
		this.attribute = attribute;
	}

	/** full constructor */
	public Article(String articleId, String title, String attribute,
			String inputData, String inputAdmin, String isView, String status,
			String content, String channelId, String author, String visitTime) {
		this.articleId = articleId;
		this.title = title;
		this.attribute = attribute;
		this.inputData = inputData;
		this.inputAdmin = inputAdmin;
		this.isView = isView;
		this.status = status;
		this.content = content;
		this.channelId = channelId;
		this.author = author;
		this.visitTime = visitTime;
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

	@Column(name = "article_id", length = 6)
	public String getArticleId() {
		return this.articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	@Column(name = "title", nullable = false, length = 2000)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "attribute", nullable = false, length = 6)
	public String getAttribute() {
		return this.attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	@Column(name = "input_data", length = 20)
	public String getInputData() {
		return this.inputData;
	}

	public void setInputData(String inputData) {
		this.inputData = inputData;
	}

	@Column(name = "input_admin", length = 20)
	public String getInputAdmin() {
		return this.inputAdmin;
	}

	public void setInputAdmin(String inputAdmin) {
		this.inputAdmin = inputAdmin;
	}

	@Column(name = "is_view", length = 2)
	public String getIsView() {
		return this.isView;
	}

	public void setIsView(String isView) {
		this.isView = isView;
	}

	@Column(name = "status", length = 6)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "channel_id", length = 6)
	public String getChannelId() {
		return this.channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	@Column(name = "author", length = 20)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "visit_time", length = 10)
	public String getVisitTime() {
		return this.visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

}
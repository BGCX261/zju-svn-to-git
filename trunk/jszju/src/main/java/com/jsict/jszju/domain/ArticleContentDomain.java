/**
 * ArticleContentDomain.java        2009-11-8 下午01:19:03
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.domain;


import com.jsict.base.BaseDomain;
import com.jsict.base.util.Text;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class ArticleContentDomain extends BaseDomain {

	// Fields

	private String id;
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
	@Text
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Text
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	@Text
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Text
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	@Text
	public String getInputData() {
		return inputData;
	}
	public void setInputData(String inputData) {
		this.inputData = inputData;
	}
	@Text
	public String getInputAdmin() {
		return inputAdmin;
	}
	public void setInputAdmin(String inputAdmin) {
		this.inputAdmin = inputAdmin;
	}
	@Text
	public String getIsView() {
		return isView;
	}
	public void setIsView(String isView) {
		this.isView = isView;
	}
	@Text
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Text
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Text
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	@Text
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Text
	public String getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}


}

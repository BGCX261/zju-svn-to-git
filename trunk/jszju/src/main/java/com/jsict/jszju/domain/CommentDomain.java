/**
 * CommentDomain.java        2009-11-24 上午12:39:52
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.domain;

import java.util.Date;

import com.jsict.base.BaseDomain;
import com.jsict.base.util.Text;
import com.jsict.jszju.entity.Article;
import com.jsict.jszju.entity.Userinfo;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class CommentDomain extends BaseDomain {

	private String commentcontent;
	private String articleId;
	private String userName;
	private String syndicResult;
	private String syndic;
	private String commentTime;
	@Text
	public String getCommentcontent() {
		return commentcontent;
	}
	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}
	@Text
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	@Text
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Text
	public String getSyndicResult() {
		return syndicResult;
	}
	public void setSyndicResult(String syndicResult) {
		this.syndicResult = syndicResult;
	}
	@Text
	public String getSyndic() {
		return syndic;
	}
	public void setSyndic(String syndic) {
		this.syndic = syndic;
	}
	@Text
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

}

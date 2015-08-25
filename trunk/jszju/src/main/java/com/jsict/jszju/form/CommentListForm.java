/**
 * CommentListForm.java        2009-11-24 上午12:38:03
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.form;

import com.jsict.base.form.BaseListForm;
import com.jsict.jszju.domain.CommentDomain;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class CommentListForm extends BaseListForm {
	
	private CommentDomain commentDomain=new CommentDomain();

	public CommentDomain getCommentDomain() {
		return commentDomain;
	}

	public void setCommentDomain(CommentDomain commentDomain) {
		this.commentDomain = commentDomain;
	}
	

}

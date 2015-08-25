/**
 * ArticleContentForm.java        2009-11-8 下午01:17:16
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.form;

import com.jsict.base.form.BaseForm;
import com.jsict.jszju.domain.ArticleContentDomain;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class ArticleContentForm extends BaseForm {
	
	private ArticleContentDomain domain=new ArticleContentDomain();

	public ArticleContentDomain getDomain() {
		return domain;
	}

	public void setDomain(ArticleContentDomain domain) {
		this.domain = domain;
	}

}

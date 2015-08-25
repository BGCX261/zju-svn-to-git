/**
 * FileHanderListForm.java        2009-12-15 下午09:17:09
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.form;

import com.jsict.base.form.BaseListForm;
import com.jsict.jszju.domain.FileInfoDomain;
import com.jsict.jszju.entity.Fileinfo;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class FileHanderListForm extends BaseListForm {
	
	private FileInfoDomain domain=new FileInfoDomain();
	
	private Fileinfo entity=new Fileinfo();

	public FileInfoDomain getDomain() {
		return domain;
	}

	public void setDomain(FileInfoDomain domain) {
		this.domain = domain;
	}

	public Fileinfo getEntity() {
		return entity;
	}

	public void setEntity(Fileinfo entity) {
		this.entity = entity;
	}

}

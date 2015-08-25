/**
 * SchoolFellowNotesListForm.java        2009-11-28 下午06:41:57
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.form;

import com.jsict.base.form.BaseListForm;
import com.jsict.jszju.domain.SchoolFellowNotesDomain;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class SchoolFellowNotesListForm extends BaseListForm {
	
	private SchoolFellowNotesDomain domain=new SchoolFellowNotesDomain();

	public SchoolFellowNotesDomain getDomain() {
		return domain;
	}

	public void setDomain(SchoolFellowNotesDomain domain) {
		this.domain = domain;
	}

}

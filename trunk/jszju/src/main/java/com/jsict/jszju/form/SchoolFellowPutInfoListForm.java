/**
 * SchoolFellowPutInfoListForm.java        2009-11-27 下午01:44:09
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.form;

import com.jsict.base.form.BaseListForm;
import com.jsict.jszju.domain.SchoolFellowPutInfoDoamin;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class SchoolFellowPutInfoListForm extends BaseListForm {
	
	private SchoolFellowPutInfoDoamin domain=new SchoolFellowPutInfoDoamin();

	public SchoolFellowPutInfoDoamin getDomain() {
		return domain;
	}

	public void setDomain(SchoolFellowPutInfoDoamin domain) {
		this.domain = domain;
	}

}

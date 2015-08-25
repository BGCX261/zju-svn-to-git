/**
 * AdminInfoForm.java        2009-10-21 下午08:26:49
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.form;

import com.jsict.base.form.BaseListForm;
import com.jsict.jszju.domain.AdminInfoDomain;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class AdminInfoForm extends BaseListForm {
	
	private AdminInfoDomain adminInfoDomain=new AdminInfoDomain();

	public AdminInfoDomain getAdminInfoDomain() {
		return adminInfoDomain;
	}

	public void setAdminInfoDomain(AdminInfoDomain adminInfoDomain) {
		this.adminInfoDomain = adminInfoDomain;
	}

}

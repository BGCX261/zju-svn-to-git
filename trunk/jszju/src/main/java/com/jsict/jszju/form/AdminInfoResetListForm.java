/**
 * AdminInfoResetListForm.java        2009-11-14 下午01:21:09
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.form;

import com.jsict.base.form.BaseListForm;
import com.jsict.jszju.domain.AdminInfoResetDomain;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class AdminInfoResetListForm extends BaseListForm {
	
	private AdminInfoResetDomain adminInfoResetDomain=new AdminInfoResetDomain();

	public AdminInfoResetDomain getAdminInfoResetDomain() {
		return adminInfoResetDomain;
	}

	public void setAdminInfoResetDomain(AdminInfoResetDomain adminInfoResetDomain) {
		this.adminInfoResetDomain = adminInfoResetDomain;
	}
	

}

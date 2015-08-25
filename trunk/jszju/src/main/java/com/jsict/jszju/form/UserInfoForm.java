/**
 * UserInfoForm.java        2009-11-21 下午03:04:27
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.form;

import com.jsict.base.form.BaseListForm;
import com.jsict.jszju.domain.ItcastUserDomain;
import com.jsict.jszju.domain.UserInfoDomain;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class UserInfoForm extends BaseListForm {

	private UserInfoDomain userInfodomain = new UserInfoDomain();

	private ItcastUserDomain itcastUserDomain=new ItcastUserDomain();

	public UserInfoDomain getUserInfodomain() {
		return userInfodomain;
	}

	public void setUserInfodomain(UserInfoDomain userInfodomain) {
		this.userInfodomain = userInfodomain;
	}

	public ItcastUserDomain getItcastUserDomain() {
		return itcastUserDomain;
	}

	public void setItcastUserDomain(ItcastUserDomain itcastUserDomain) {
		this.itcastUserDomain = itcastUserDomain;
	}

}

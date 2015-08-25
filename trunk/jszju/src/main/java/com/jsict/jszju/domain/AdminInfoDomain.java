/**
 * AdminInfoDomain.java        2009-10-21 下午08:27:46
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.jsict.base.BaseDomain;
import com.jsict.base.util.Text;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class AdminInfoDomain extends BaseDomain {


	// Fields

	private String name;
	private String password;
	private String role;
	private String isable;


	@Text(label = "")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Text(label = "")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Text(label = "")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	@Text(label = "")
	public String getIsable() {
		return isable;
	}

	public void setIsable(String isable) {
		this.isable = isable;
	}




}

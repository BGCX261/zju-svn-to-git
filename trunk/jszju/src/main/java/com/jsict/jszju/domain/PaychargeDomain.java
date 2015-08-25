/**
 * Paycharge.java        2009-11-25 下午11:25:28
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.domain;

import com.jsict.base.BaseDomain;
import com.jsict.base.util.Text;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class PaychargeDomain extends BaseDomain {
	
	private String name;
	private String money;
	private String contribute;
	
	@Text
	public String getContribute() {
		return contribute;
	}
	public void setContribute(String contribute) {
		this.contribute = contribute;
	}
	@Text
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	@Text
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}

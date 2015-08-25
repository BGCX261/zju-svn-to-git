/**
 * PaychargeListFrom.java        2009-11-25 下午11:27:24
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.form;

import com.jsict.base.form.BaseListForm;
import com.jsict.jszju.domain.PaychargeDomain;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class PaychargeListFrom extends BaseListForm {
	
	private PaychargeDomain  paychargeDomain=new PaychargeDomain();

	public PaychargeDomain getPaychargeDomain() {
		return paychargeDomain;
	}

	public void setPaychargeDomain(PaychargeDomain paychargeDomain) {
		this.paychargeDomain = paychargeDomain;
	}

}

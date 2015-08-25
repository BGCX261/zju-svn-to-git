/**
 * IAdminResetInfoService.java        2009-11-15 下午06:41:41
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.service;

import com.jsict.base.IBaseService;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.domain.AdminInfoResetDomain;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public interface IAdminResetInfoService extends IBaseService {
	
	public void  resetAdminInfo(AdminInfoResetDomain domain) throws ApplicationException, SystemException;

}

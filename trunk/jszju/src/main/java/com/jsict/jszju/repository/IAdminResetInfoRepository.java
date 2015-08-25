/**
 * IAdminResetInfoRepository.java        2009-11-15 下午06:57:11
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.repository;

import java.util.List;

import com.jsict.base.IBaseRepository;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.entity.Admininfo;
import com.jsict.jszju.entity.Adminrestinfo;
import com.jsict.jszju.entity.Article;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public interface IAdminResetInfoRepository extends IBaseRepository {
	
	public Adminrestinfo getAdminResetInfo(Integer id) throws ApplicationException, SystemException;
	
	public Adminrestinfo saveAdminResetInfo(Adminrestinfo adminrestinfo);
}

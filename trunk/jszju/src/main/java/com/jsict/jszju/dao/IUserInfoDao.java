/**
 * IUserInfoDao.java        2009-10-14 下午05:12:08
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.dao;

import com.jsict.base.dao.IBaseDao;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.entity.Userinfo;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">蔡锷</a>
 * @version 1.0
 */
public interface IUserInfoDao extends IBaseDao<Userinfo> {
	
	public Integer  getUserInfoId(String username) throws SystemException,
    ApplicationException;
	

}

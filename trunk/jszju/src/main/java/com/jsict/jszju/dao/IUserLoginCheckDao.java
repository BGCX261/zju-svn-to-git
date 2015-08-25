/**
 * IUserLoginCheckDao.java        2009-11-21 下午03:33:52
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.dao;

import com.jsict.base.dao.IBaseDao;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.entity.ItcastUser;
import com.jsict.jszju.entity.Userinfo;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public interface IUserLoginCheckDao extends IBaseDao<Userinfo> {
	
	public boolean checkUserInfo(Userinfo entity) throws SystemException,
    ApplicationException;
    
    public boolean isUserNameExist(Userinfo entity) throws SystemException,
    ApplicationException;


}

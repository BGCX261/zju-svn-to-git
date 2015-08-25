/**
 * IUserLoginCheckService.java        2009-11-21 下午03:26:05
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.service;

import com.jsict.base.IBaseService;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.domain.ItcastUserDomain;
import com.jsict.jszju.domain.UserInfoDomain;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public interface IUserLoginCheckService extends IBaseService {
	
	public boolean checkUserInfo(UserInfoDomain domain) throws SystemException,
    ApplicationException;
	
	public boolean checkUserInfo(ItcastUserDomain domain) throws SystemException,
    ApplicationException;
	
	public boolean isUserNameExist(UserInfoDomain domain) throws SystemException,
    ApplicationException;
	
	public PagedList<UserInfoDomain> getUserInfoPagedList(
			EntityFilter filter, Integer pageNo, Integer pageSize)
			throws ApplicationException, SystemException;

	public void addUserInfo(UserInfoDomain domain) throws SystemException,
			ApplicationException;
	
	public void updateUserInfo(UserInfoDomain domain) throws SystemException,
			ApplicationException;
	
	public void delUserInfo(UserInfoDomain domain) throws SystemException,
	ApplicationException;
	
	public UserInfoDomain getUserInfo(String id) throws ApplicationException, SystemException;

}

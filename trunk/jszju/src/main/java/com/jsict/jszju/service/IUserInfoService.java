/**
 * IUserInfoService.java        2009-10-14 下午03:45:24
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.service;

import javax.servlet.http.HttpServletRequest;

import com.jsict.base.IBaseService;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.domain.UserInfoDomain;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">蔡锷</a>
 * @version 1.0
 */
public interface IUserInfoService extends IBaseService {
	
	public void saveUserInfo(UserInfoDomain domain) throws SystemException,
	        ApplicationException;
	
	public void updateUserInfo(UserInfoDomain domain) throws SystemException,
    ApplicationException;
	
	public UserInfoDomain getUserInfo(Integer id) throws SystemException,
    ApplicationException;
	
	public Integer  getUserInfoId(String username) throws SystemException,
    ApplicationException;
	
	public String getUserNameFormCookie(HttpServletRequest request)throws SystemException,
    ApplicationException;

}

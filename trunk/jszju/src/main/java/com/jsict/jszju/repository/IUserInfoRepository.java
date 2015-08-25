/**
 * IUserInfoRepository.java        2009-10-14 下午04:59:52
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.repository;

import com.jsict.base.IBaseRepository;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.entity.Userinfo;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">蔡锷</a>
 * @version 1.0
 */
public interface IUserInfoRepository extends IBaseRepository {
	
	public Userinfo saveUserInfo(Userinfo entity) throws SystemException,
    ApplicationException;
	
	public Userinfo getUserInfo(Integer id) throws SystemException,
    ApplicationException;
	
	public Integer  getUserInfoId(String username) throws SystemException,
    ApplicationException;
}

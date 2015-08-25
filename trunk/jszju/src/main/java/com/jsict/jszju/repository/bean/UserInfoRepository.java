/**
 * UserInfoRepository.java        2009-10-14 下午05:00:52
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.repository.bean;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.BaseRepository;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.dao.IUserInfoDao;
import com.jsict.jszju.entity.Userinfo;
import com.jsict.jszju.repository.IUserInfoRepository;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: caie@jsict.com">蔡锷</a>
 * @version 1.0
 */
public class UserInfoRepository extends BaseRepository implements
		IUserInfoRepository {
	private IUserInfoDao userInfoDao;

	@Required
	public void setUserInfoDao(IUserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}


	public Userinfo saveUserInfo(Userinfo entity) {
		return (Userinfo) userInfoDao.save(entity);
	}

	public Userinfo getUserInfo(Integer id) throws SystemException,
			ApplicationException {
		return (Userinfo) userInfoDao.get(id);
	}

	public Integer getUserInfoId(String username) throws SystemException,
			ApplicationException {
		
		return userInfoDao.getUserInfoId(username);
	}

}

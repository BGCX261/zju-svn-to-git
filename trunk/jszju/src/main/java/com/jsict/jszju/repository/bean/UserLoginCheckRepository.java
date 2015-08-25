/**
 * UserLoginCheckRepository.java        2009-11-21 下午03:31:20
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.repository.bean;

import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.BaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.dao.IItcastUserDao;
import com.jsict.jszju.dao.IUserLoginCheckDao;
import com.jsict.jszju.entity.ItcastUser;
import com.jsict.jszju.entity.Userinfo;
import com.jsict.jszju.repository.IUserLoginCheckRepository;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class UserLoginCheckRepository extends BaseRepository implements
		IUserLoginCheckRepository {

	private IUserLoginCheckDao userLoginCheckDao;
	
	private IItcastUserDao    itcastUserDao;

	@Required
	public void setUserLoginCheckDao(IUserLoginCheckDao userLoginCheckDao) {
		this.userLoginCheckDao = userLoginCheckDao;
	}
	@Required
	public void setItcastUserDao(IItcastUserDao itcastUserDao) {
		this.itcastUserDao = itcastUserDao;
	}

	public boolean checkUserInfo(Userinfo entity) throws SystemException,
			ApplicationException {

		return userLoginCheckDao.checkUserInfo(entity);

	}

	public boolean checkUserInfo(ItcastUser entity) throws SystemException,
			ApplicationException {
		return itcastUserDao.checkUserInfo(entity);
	}

	public boolean isUserNameExist(Userinfo entity) throws SystemException,
			ApplicationException {

		return userLoginCheckDao.isUserNameExist(entity);

	}

	public void delUserInfo(Userinfo entity) {

		userLoginCheckDao.delete(entity);
	}

	public Userinfo getUserInfo(Integer id) {

		return userLoginCheckDao.get(id);
	}

	public PagedList<Userinfo> getUserInfoPagedList(EntityFilter tf,
			Integer pageNo, Integer pageSize) {

		return userLoginCheckDao.getPagedList(tf, pageNo, pageSize);
	}

	public Userinfo saveUserInfo(Userinfo entity) {

		return userLoginCheckDao.save(entity);
	}

}

/**
 * UserLoginCheckService.java        2009-11-21 下午03:26:56
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.service.bean;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bbs.service.UserService;

import com.jsict.base.BaseService;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.ConvertUtil;
import com.jsict.base.util.ListUtil;
import com.jsict.base.util.StringUtil;
import com.jsict.jszju.domain.ItcastUserDomain;
import com.jsict.jszju.domain.UserInfoDomain;
import com.jsict.jszju.entity.ItcastUser;
import com.jsict.jszju.entity.Userinfo;
import com.jsict.jszju.repository.IUserLoginCheckRepository;
import com.jsict.jszju.service.IUserLoginCheckService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
@Transactional
public class UserLoginCheckService extends BaseService implements
		IUserLoginCheckService {

	private IUserLoginCheckRepository userLoginCheckRepository;
	
	
	@Required
	public void setUserLoginCheckRepository(
			IUserLoginCheckRepository userLoginCheckRepository) {
		this.userLoginCheckRepository = userLoginCheckRepository;
	}

	public boolean checkUserInfo(UserInfoDomain domain) throws SystemException,
			ApplicationException {
		Userinfo entity = new Userinfo();
		entity.setName(domain.getName());
		entity.setPassword(domain.getPassword());
		return userLoginCheckRepository.checkUserInfo(entity);

	}

	public boolean checkUserInfo(ItcastUserDomain domain)
			throws SystemException, ApplicationException {
		ItcastUser entity = new ItcastUser();
		String psw=DigestUtils.md5Hex(domain.getPassword());
		entity.setLoginName(domain.getLoginName());
		entity.setPassword(psw);
		return userLoginCheckRepository.checkUserInfo(entity);
	}
	
	

	public boolean isUserNameExist(UserInfoDomain domain)
			throws SystemException, ApplicationException {
		Userinfo entity = new Userinfo();
		ConvertUtil.domain2entity(domain, entity);
		return userLoginCheckRepository.isUserNameExist(entity);

	}

	public void addUserInfo(UserInfoDomain domain) throws SystemException,
			ApplicationException {
		Userinfo entity = new Userinfo();
		if (ConvertUtil.domain2entity(domain, entity)) {
		}
		entity = this.userLoginCheckRepository.saveUserInfo(entity);
	}

	public void delUserInfo(UserInfoDomain domain) throws SystemException,
			ApplicationException {

		Userinfo entity = new Userinfo();
		if (ConvertUtil.domain2entity(domain, entity)) {
		}
		userLoginCheckRepository.delUserInfo(entity);

	}

	public UserInfoDomain getUserInfo(String id) throws ApplicationException,
			SystemException {
		UserInfoDomain domain = new UserInfoDomain();
		Userinfo entity = new Userinfo();
		if (StringUtil.isLong(id)) {
			entity = userLoginCheckRepository.getUserInfo(Integer.parseInt(id));
			ConvertUtil.entity2domain(entity, domain);
		}
		return domain;
	}

	public PagedList<UserInfoDomain> getUserInfoPagedList(EntityFilter filter,
			Integer pageNo, Integer pageSize) throws ApplicationException,
			SystemException {
		PagedList<UserInfoDomain> listDomain = new PagedList<UserInfoDomain>();
		PagedList<Userinfo> listEntity = this.userLoginCheckRepository
				.getUserInfoPagedList(filter, pageNo, pageSize);
		for (Userinfo entity : listEntity) {
			UserInfoDomain sfDomain = new UserInfoDomain();
			ConvertUtil.entity2domain(entity, sfDomain);
			listDomain.add(sfDomain);
		}
		ListUtil.clonePagedInfo(listDomain, listEntity);
		return listDomain;
	}

	public void updateUserInfo(UserInfoDomain domain) throws SystemException,
			ApplicationException {
		if (!StringUtil.isLong(domain.getId())) {
		}
		Userinfo entity = this.userLoginCheckRepository.getUserInfo(Integer
				.parseInt(domain.getId()));
		if (ConvertUtil.domain2entity(domain, entity)) {
		}
		userLoginCheckRepository.saveUserInfo(entity);
	}

}

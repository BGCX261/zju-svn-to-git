/**
 * UserInfoService.java        2009-10-14 下午04:04:39
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.service.bean;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.jsict.base.BaseService;
import com.jsict.base.util.ConvertUtil;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.domain.UserInfoDomain;
import com.jsict.jszju.entity.Userinfo;
import com.jsict.jszju.repository.IUserInfoRepository;
import com.jsict.jszju.service.IUserInfoService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: caie@jsict.com">蔡锷</a>
 * @version 1.0
 */
@Transactional
public class UserInfoService extends BaseService implements IUserInfoService {

	private static final String NAME = "name";

	private IUserInfoRepository userInfoRepository;

	@Required
	public void setUserInfoRepository(IUserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
	}

	public void saveUserInfo(UserInfoDomain domain) throws SystemException,
			ApplicationException {

		Userinfo entity = new Userinfo();
		if (ConvertUtil.domain2entity(domain, entity)) {
			throw new ApplicationException();
		}
		entity = userInfoRepository.saveUserInfo(entity);

	}

	public UserInfoDomain getUserInfo(Integer id) throws SystemException,
			ApplicationException {
		UserInfoDomain domain = new UserInfoDomain();
		Userinfo entity = new Userinfo();
		entity = userInfoRepository.getUserInfo(id);
		ConvertUtil.entity2domain(entity, domain);
		return domain;
	}

	public Integer getUserInfoId(String username) throws SystemException,
			ApplicationException {

		return userInfoRepository.getUserInfoId(username);
	}

	public String getUserNameFormCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String cname = null;
		if (cookies != null) {
			for (Cookie c : cookies) {
				cname = c.getName();
				if (NAME.equalsIgnoreCase(cname)) {
					return c.getValue();
				}
			}
		}
		return null;
	}

	public void updateUserInfo(UserInfoDomain domain) throws SystemException,
			ApplicationException {
		UserInfoDomain  udomain=new UserInfoDomain();
		Userinfo entity = new Userinfo();
		udomain=this.getUserInfo(Integer.parseInt(domain.getId()));
		if (ConvertUtil.domain2entity(udomain, entity)) {
			throw new ApplicationException();
		}
		if (ConvertUtil.domain2entity(domain, entity)) {
			throw new ApplicationException();
		}
		entity = userInfoRepository.saveUserInfo(entity);

	}

}

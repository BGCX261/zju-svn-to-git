/**
 * IUserLoginCheckRepository.java        2009-11-21 下午03:30:46
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.repository;

import com.jsict.base.IBaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.domain.UserInfoDomain;
import com.jsict.jszju.entity.ItcastUser;
import com.jsict.jszju.entity.Schoolfellownotes;
import com.jsict.jszju.entity.Userinfo;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public interface IUserLoginCheckRepository extends IBaseRepository {
	
	public boolean checkUserInfo(Userinfo entity) throws SystemException,
    ApplicationException;
    
	public boolean checkUserInfo(ItcastUser entity) throws SystemException,
    ApplicationException;
	
    public boolean isUserNameExist(Userinfo entity) throws SystemException,
    ApplicationException;
    
	public PagedList<Userinfo> getUserInfoPagedList(EntityFilter tf,
            Integer pageNo, Integer pageSize);
	
	public Userinfo getUserInfo(Integer id);
	
	public void delUserInfo(Userinfo entity);
	
	public Userinfo saveUserInfo(Userinfo entity);

}

/**
 * IAdminInfoCheckService.java        2009-11-13 下午09:06:13
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.service;

import java.util.List;

import com.jsict.base.IBaseService;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.domain.AdminInfoDomain;
import com.jsict.jszju.domain.ArticleContentDomain;
import com.jsict.jszju.domain.UserInfoDomain;
import com.jsict.jszju.entity.Admininfo;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public interface IAdminInfoCheckService extends IBaseService {

	public List<AdminInfoDomain> getAdmininfoList()
			throws ApplicationException, SystemException;

	public AdminInfoDomain getAdminInfo(String id) throws ApplicationException,
			SystemException;

	public void saveAdmininfo(AdminInfoDomain domain)
			throws ApplicationException, SystemException;
	
	public void insertAdmininfo(AdminInfoDomain domain)
	throws ApplicationException, SystemException;
	
	public PagedList<AdminInfoDomain> getAdmininfoPagedList(
			EntityFilter filter, Integer pageNo, Integer pageSize)
			throws ApplicationException, SystemException;
	
    public void updateAdminInfo(
    		AdminInfoDomain domain) throws SystemException,
        ApplicationException;
    
	public Admininfo isAdminInputExist(AdminInfoDomain domain) throws SystemException,
    ApplicationException;

}

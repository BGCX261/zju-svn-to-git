/**
 * IAdminInfoCheckRepository.java        2009-11-13 下午09:10:57
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.repository;

import java.util.List;

import com.jsict.base.IBaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.entity.Admininfo;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public interface IAdminInfoCheckRepository extends IBaseRepository {
	
	public Admininfo getAdminInfo(Long id) throws ApplicationException, SystemException;
	
	public List<Admininfo> getAdmininfoList()
    throws ApplicationException, SystemException;
	
	public void saveAdmininfo(Admininfo entity)
	throws ApplicationException, SystemException;
	
	public PagedList<Admininfo> getAdmininfoPagedList(EntityFilter tf,
            Integer pageNo, Integer pageSize);
	
	  public Admininfo isAdminInputExist(Admininfo entity) throws SystemException,
	    ApplicationException;
	

}

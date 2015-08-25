/**
 * ISchooleFellowInfoConsultService.java        2009-11-28 上午12:38:25
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.service;

import com.jsict.base.IBaseService;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.domain.SchoolFellowPutInfoDoamin;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public interface ISchooleFellowInfoConsultService extends IBaseService {

	public PagedList<SchoolFellowPutInfoDoamin> getSchooleFellowInfoConsultPagedList(
			EntityFilter filter, Integer pageNo, Integer pageSize)
			throws ApplicationException, SystemException;

	public void addSchooleFellowInfoConsult(SchoolFellowPutInfoDoamin domain) throws SystemException,
			ApplicationException;

	public void updateSchooleFellowInfoConsult(SchoolFellowPutInfoDoamin domain) throws SystemException,
			ApplicationException;
	
	public SchoolFellowPutInfoDoamin getSchooleFellowInfoConsult(String id) throws ApplicationException, SystemException;

}

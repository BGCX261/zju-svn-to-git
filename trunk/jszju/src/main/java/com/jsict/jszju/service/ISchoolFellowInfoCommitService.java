/**
 * ISchoolFellowInfoCommitService.java        2009-11-27 下午10:20:07
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
import com.jsict.jszju.domain.SchoolFellowForHelpDomain;
import com.jsict.jszju.entity.ItcastTopic;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public interface ISchoolFellowInfoCommitService extends IBaseService {

	public PagedList<SchoolFellowForHelpDomain> getSchoolFelloHelpPagedList(
			EntityFilter filter, Integer pageNo, Integer pageSize)
			throws ApplicationException, SystemException;

	public void addSchoolFelloHelp(SchoolFellowForHelpDomain domain) throws SystemException,
			ApplicationException;

	public void updateSchoolFelloHelp(SchoolFellowForHelpDomain domain) throws SystemException,
			ApplicationException;
	
	public SchoolFellowForHelpDomain getSchoolFelloHelp(String id) throws ApplicationException, SystemException;
	
	public List<ItcastTopic> getItcastTopic(String forumid) throws ApplicationException, SystemException;

}

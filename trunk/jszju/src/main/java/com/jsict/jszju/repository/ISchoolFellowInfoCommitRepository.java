/**
 * ISchoolFellowInfoCommitRepository.java        2009-11-27 下午10:48:55
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
import com.jsict.jszju.entity.ItcastTopic;
import com.jsict.jszju.entity.Schoolfellowhelp;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public interface ISchoolFellowInfoCommitRepository extends IBaseRepository {
	
	public PagedList<Schoolfellowhelp> getSchoolFelloHelpPagedList(EntityFilter tf,
            Integer pageNo, Integer pageSize);
	
	public Schoolfellowhelp getSchoolFelloHelp(Long id);
	
	public Schoolfellowhelp saveSchoolFelloHelp(Schoolfellowhelp schoolfellowhelp);
	
	public List<ItcastTopic> getItcastTopic(String forumid) throws ApplicationException, SystemException;

}

/**
 * ISchooleFellowInfoConsultRepository.java        2009-11-28 上午12:39:26
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.repository;

import com.jsict.base.IBaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.jszju.entity.Schoolfellowhelp;
import com.jsict.jszju.entity.Schoolfellowinfo;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public interface ISchooleFellowInfoConsultRepository extends IBaseRepository {
	
	public PagedList<Schoolfellowinfo> getSchooleFellowInfoConsultPagedList(EntityFilter tf,
            Integer pageNo, Integer pageSize);
	
	public Schoolfellowinfo getSchooleFellowInfoConsult(Long id);
	
	public Schoolfellowinfo saveSchooleFellowInfoConsult(Schoolfellowinfo schoolfellowinfo);

}

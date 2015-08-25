/**
 * SchooleFellowInfoConsultRepository.java        2009-11-28 上午12:39:43
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.repository.bean;

import com.jsict.base.BaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.jszju.dao.ISchooleFellowInfoConsultDao;
import com.jsict.jszju.entity.Schoolfellowinfo;
import com.jsict.jszju.repository.ISchooleFellowInfoConsultRepository;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class SchooleFellowInfoConsultRepository extends BaseRepository
		implements ISchooleFellowInfoConsultRepository {

	private ISchooleFellowInfoConsultDao  schooleFellowInfoConsultDao;
	
	public void setSchooleFellowInfoConsultDao(
			ISchooleFellowInfoConsultDao schooleFellowInfoConsultDao) {
		this.schooleFellowInfoConsultDao = schooleFellowInfoConsultDao;
	}
	
	public Schoolfellowinfo getSchooleFellowInfoConsult(Long id) {
		
		Integer id1=Integer.parseInt(String.valueOf(id));
		return schooleFellowInfoConsultDao.get(id1);
	}

	public PagedList<Schoolfellowinfo> getSchooleFellowInfoConsultPagedList(
			EntityFilter tf, Integer pageNo, Integer pageSize) {
		return schooleFellowInfoConsultDao.getPagedList(tf, pageNo, pageSize);
		}

	public Schoolfellowinfo saveSchooleFellowInfoConsult(
			Schoolfellowinfo schoolfellowinfo) {

		return schooleFellowInfoConsultDao.save(schoolfellowinfo);
	}


}

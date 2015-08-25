/**
 * SchoolFellowNotesRepository.java        2009-11-28 下午06:36:26
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.repository.bean;

import com.jsict.base.BaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.jszju.dao.ISchoolFellowNotesDao;
import com.jsict.jszju.entity.Schoolfellownotes;
import com.jsict.jszju.repository.ISchoolFellowNotesRepository;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class SchoolFellowNotesRepository extends BaseRepository implements
		ISchoolFellowNotesRepository {
	
	private ISchoolFellowNotesDao  schoolFellowNotesDao;
	
	public void setSchoolFellowNotesDao(ISchoolFellowNotesDao schoolFellowNotesDao) {
		this.schoolFellowNotesDao = schoolFellowNotesDao;
	}


	public Schoolfellownotes getSchoolFellowNotes(Long id) {
		
		Integer id1=Integer.parseInt(String.valueOf(id));
		return schoolFellowNotesDao.get(id1);
	}

	public PagedList<Schoolfellownotes> getSchoolFellowNotesPagedList(
			EntityFilter tf, Integer pageNo, Integer pageSize) {
		return schoolFellowNotesDao.getPagedList(tf, pageNo, pageSize);
		}

	public Schoolfellownotes saveSchoolFellowNotes(
			Schoolfellownotes schoolfellownotes) {

		return schoolFellowNotesDao.save(schoolfellownotes);
	}
	
	public void delSchoolFellowNotes(Schoolfellownotes entity) {
		
		 schoolFellowNotesDao.delete(entity);
	}

}

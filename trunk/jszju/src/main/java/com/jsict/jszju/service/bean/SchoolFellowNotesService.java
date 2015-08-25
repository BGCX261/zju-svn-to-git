/**
 * SchoolFellowNotesService.java        2009-11-28 下午06:35:37
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.service.bean;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.jsict.base.BaseService;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.ConvertUtil;
import com.jsict.base.util.ListUtil;
import com.jsict.base.util.StringUtil;
import com.jsict.jszju.domain.SchoolFellowForHelpDomain;
import com.jsict.jszju.domain.SchoolFellowNotesDomain;
import com.jsict.jszju.entity.Schoolfellowhelp;
import com.jsict.jszju.entity.Schoolfellownotes;
import com.jsict.jszju.repository.ISchoolFellowNotesRepository;
import com.jsict.jszju.service.ISchoolFellowNotesService;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
@Transactional
public class SchoolFellowNotesService extends BaseService implements
		ISchoolFellowNotesService {
	
	private ISchoolFellowNotesRepository  schoolFellowNotesRepository;
	@Required
	public void setSchoolFellowNotesRepository(
			ISchoolFellowNotesRepository schoolFellowNotesRepository) {
		this.schoolFellowNotesRepository = schoolFellowNotesRepository;
	}

	public void addSchoolFellowNotes(SchoolFellowNotesDomain domain)
			throws SystemException, ApplicationException {
		Schoolfellownotes entity = new Schoolfellownotes();
		if (ConvertUtil.domain2entity(domain, entity)) {
		}
		entity = this.schoolFellowNotesRepository.saveSchoolFellowNotes(entity);
	}

	public SchoolFellowNotesDomain getSchoolFellowNotes(String id)
			throws ApplicationException, SystemException {
		SchoolFellowNotesDomain domain = new SchoolFellowNotesDomain();
		Schoolfellownotes entity = new Schoolfellownotes();
		if (StringUtil.isLong(id)) {
			entity = schoolFellowNotesRepository.getSchoolFellowNotes(Long
					.parseLong(id));
			ConvertUtil.entity2domain(entity, domain);
		}
		return domain;
	}

	public PagedList<SchoolFellowNotesDomain> getSchoolFellowNotesPagedList(
			EntityFilter filter, Integer pageNo, Integer pageSize)
			throws ApplicationException, SystemException {
		PagedList<SchoolFellowNotesDomain> listDomain = new PagedList<SchoolFellowNotesDomain>();
		PagedList<Schoolfellownotes> listEntity = this.schoolFellowNotesRepository
				.getSchoolFellowNotesPagedList(filter, pageNo, pageSize);
		for (Schoolfellownotes entity : listEntity) {
			SchoolFellowNotesDomain sfDomain = new SchoolFellowNotesDomain();
			ConvertUtil.entity2domain(entity, sfDomain);
			listDomain.add(sfDomain);
		}
		ListUtil.clonePagedInfo(listDomain, listEntity);
		return listDomain;
	}

	public void updateSchoolFellowNotes(SchoolFellowNotesDomain domain)
			throws SystemException, ApplicationException {
		if (!StringUtil.isLong(domain.getId())) {
		}
		Schoolfellownotes entity = this.schoolFellowNotesRepository.getSchoolFellowNotes(Long
				.parseLong(domain.getId()));
		if (ConvertUtil.domain2entity(domain, entity)) {
		}
		schoolFellowNotesRepository.saveSchoolFellowNotes(entity);
	}
	
	public void delSchoolFellowNotes(SchoolFellowNotesDomain domain)
	throws SystemException, ApplicationException {
		
		Schoolfellownotes entity=new Schoolfellownotes();
		if (ConvertUtil.domain2entity(domain, entity)) {
		}
		schoolFellowNotesRepository.delSchoolFellowNotes(entity);
	
	}

}

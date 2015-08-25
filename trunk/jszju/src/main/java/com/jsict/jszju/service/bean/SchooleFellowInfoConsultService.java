/**
 * SchooleFellowInfoConsultService.java        2009-11-28 上午12:38:50
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
import com.jsict.jszju.domain.SchoolFellowPutInfoDoamin;
import com.jsict.jszju.entity.Schoolfellowhelp;
import com.jsict.jszju.entity.Schoolfellowinfo;
import com.jsict.jszju.repository.ISchooleFellowInfoConsultRepository;
import com.jsict.jszju.service.ISchooleFellowInfoConsultService;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
@Transactional
public class SchooleFellowInfoConsultService extends BaseService implements
		ISchooleFellowInfoConsultService {
	
	private ISchooleFellowInfoConsultRepository  schooleFellowInfoConsultRepository;
	@Required
	public void setSchooleFellowInfoConsultRepository(
			ISchooleFellowInfoConsultRepository schooleFellowInfoConsultRepository) {
		this.schooleFellowInfoConsultRepository = schooleFellowInfoConsultRepository;
	}


	public void addSchooleFellowInfoConsult(SchoolFellowPutInfoDoamin domain)
			throws SystemException, ApplicationException {
		Schoolfellowinfo entity = new Schoolfellowinfo();
		if (ConvertUtil.domain2entity(domain, entity)) {
		}
		entity = this.schooleFellowInfoConsultRepository.saveSchooleFellowInfoConsult(entity);
	}

	public SchoolFellowPutInfoDoamin getSchooleFellowInfoConsult(String id)
			throws ApplicationException, SystemException {
		SchoolFellowPutInfoDoamin domain = new SchoolFellowPutInfoDoamin();
		Schoolfellowinfo entity = new Schoolfellowinfo();
		if (StringUtil.isLong(id)) {
			entity = schooleFellowInfoConsultRepository.getSchooleFellowInfoConsult(Long
					.parseLong(id));
			ConvertUtil.entity2domain(entity, domain);
		}
		return domain;
	}

	public PagedList<SchoolFellowPutInfoDoamin> getSchooleFellowInfoConsultPagedList(
			EntityFilter filter, Integer pageNo, Integer pageSize)
			throws ApplicationException, SystemException {
		PagedList<SchoolFellowPutInfoDoamin> listDomain = new PagedList<SchoolFellowPutInfoDoamin>();
		PagedList<Schoolfellowinfo> listEntity = this.schooleFellowInfoConsultRepository
				.getSchooleFellowInfoConsultPagedList(filter, pageNo, pageSize);
		for (Schoolfellowinfo entity : listEntity) {
			SchoolFellowPutInfoDoamin sfDomain = new SchoolFellowPutInfoDoamin();
			ConvertUtil.entity2domain(entity, sfDomain);
			listDomain.add(sfDomain);
		}
		ListUtil.clonePagedInfo(listDomain, listEntity);
		return listDomain;
	}

	public void updateSchooleFellowInfoConsult(SchoolFellowPutInfoDoamin domain)
			throws SystemException, ApplicationException {
		if (!StringUtil.isLong(domain.getId())) {
		}
		Schoolfellowinfo entity = this.schooleFellowInfoConsultRepository.getSchooleFellowInfoConsult(Long
				.parseLong(domain.getId()));
		if (ConvertUtil.domain2entity(domain, entity)) {
		}
		schooleFellowInfoConsultRepository.saveSchooleFellowInfoConsult(entity);
	}

}

/**
 * SchoolFellowInfoCommitService.java        2009-11-27 下午10:41:57
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.service.bean;

import java.util.List;

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
import com.jsict.jszju.entity.ItcastTopic;
import com.jsict.jszju.entity.Schoolfellowhelp;
import com.jsict.jszju.repository.ISchoolFellowInfoCommitRepository;
import com.jsict.jszju.service.ISchoolFellowInfoCommitService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
@Transactional
public class SchoolFellowInfoCommitService extends BaseService implements
		ISchoolFellowInfoCommitService {

	private ISchoolFellowInfoCommitRepository schoolFellowInfoCommitRepository;

	@Required
	public void setSchoolFellowInfoCommitRepository(
			ISchoolFellowInfoCommitRepository schoolFellowInfoCommitRepository) {
		this.schoolFellowInfoCommitRepository = schoolFellowInfoCommitRepository;
	}

	/**
	 * <p>
	 * Description: The addSchoolFelloHelp
	 * </p>
	 * 
	 * @param domain
	 * @throws SystemException
	 * @throws ApplicationException
	 * @author: caie
	 * @update: [updatedate] [changer][change description]
	 */

	public void addSchoolFelloHelp(SchoolFellowForHelpDomain domain)
			throws SystemException, ApplicationException {
		Schoolfellowhelp entity = new Schoolfellowhelp();
		if (ConvertUtil.domain2entity(domain, entity)) {
		}
		entity = this.schoolFellowInfoCommitRepository
				.saveSchoolFelloHelp(entity);
	}

	/**
	 * <p>
	 * Description: The getSchoolFelloHelp
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws ApplicationException
	 * @throws SystemException
	 * @author: caie
	 * @update: [updatedate] [changer][change description]
	 */

	public SchoolFellowForHelpDomain getSchoolFelloHelp(String id)
			throws ApplicationException, SystemException {
		SchoolFellowForHelpDomain domain = new SchoolFellowForHelpDomain();
		Schoolfellowhelp entity = new Schoolfellowhelp();
		if (StringUtil.isLong(id)) {
			entity = schoolFellowInfoCommitRepository.getSchoolFelloHelp(Long
					.parseLong(id));
			ConvertUtil.entity2domain(entity, domain);
		}
		return domain;
	}

	/**
	 * <p>
	 * Description: The getSchoolFelloHelpPagedList
	 * </p>
	 * 
	 * @param filter
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws ApplicationException
	 * @throws SystemException
	 * @author: caie
	 * @update: [updatedate] [changer][change description]
	 */

	public PagedList<SchoolFellowForHelpDomain> getSchoolFelloHelpPagedList(
			EntityFilter filter, Integer pageNo, Integer pageSize)
			throws ApplicationException, SystemException {
		PagedList<SchoolFellowForHelpDomain> listDomain = new PagedList<SchoolFellowForHelpDomain>();
		PagedList<Schoolfellowhelp> listEntity = this.schoolFellowInfoCommitRepository
				.getSchoolFelloHelpPagedList(filter, pageNo, pageSize);
		for (Schoolfellowhelp entity : listEntity) {
			SchoolFellowForHelpDomain sfDomain = new SchoolFellowForHelpDomain();
			ConvertUtil.entity2domain(entity, sfDomain);
			listDomain.add(sfDomain);
		}
		ListUtil.clonePagedInfo(listDomain, listEntity);
		return listDomain;
	}

	/**
	 * <p>
	 * Description: The updateSchoolFelloHelp
	 * </p>
	 * 
	 * @param domain
	 * @throws SystemException
	 * @throws ApplicationException
	 * @author: caie
	 * @update: [updatedate] [changer][change description]
	 */

	public void updateSchoolFelloHelp(SchoolFellowForHelpDomain domain)
			throws SystemException, ApplicationException {
		if (!StringUtil.isLong(domain.getId())) {
		}
		Schoolfellowhelp entity = this.schoolFellowInfoCommitRepository
				.getSchoolFelloHelp(Long.parseLong(domain.getId()));
		if (ConvertUtil.domain2entity(domain, entity)) {
		}
		schoolFellowInfoCommitRepository.saveSchoolFelloHelp(entity);
	}

	/**
	 * 
	 */
	public List<ItcastTopic> getItcastTopic(String forumid)
			throws ApplicationException, SystemException {
		return schoolFellowInfoCommitRepository.getItcastTopic(forumid);
	}

}

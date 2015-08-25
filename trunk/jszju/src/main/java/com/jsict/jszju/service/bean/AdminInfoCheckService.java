/**
 * AdminInfoCheckService.java        2009-11-13 下午09:06:50
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.service.bean;

import java.util.ArrayList;
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
import com.jsict.jszju.domain.AdminInfoDomain;
import com.jsict.jszju.domain.AdminInfoResetDomain;
import com.jsict.jszju.entity.Admininfo;
import com.jsict.jszju.entity.Adminrestinfo;
import com.jsict.jszju.entity.Userinfo;
import com.jsict.jszju.repository.IAdminInfoCheckRepository;
import com.jsict.jszju.service.IAdminInfoCheckService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */

@Transactional
public class AdminInfoCheckService extends BaseService implements
		IAdminInfoCheckService {

	private IAdminInfoCheckRepository adminInfoCheckRepository;

	@Required
	public void setAdminInfoCheckRepository(
			IAdminInfoCheckRepository adminInfoCheckRepository) {
		this.adminInfoCheckRepository = adminInfoCheckRepository;
	}

	public AdminInfoDomain getAdminInfo(String id) throws ApplicationException,
			SystemException {
		AdminInfoDomain domain = new AdminInfoDomain();
		if (StringUtil.isLong(id)) {
			Admininfo entity = adminInfoCheckRepository.getAdminInfo(Long
					.parseLong(id));
			ConvertUtil.entity2domain(entity, domain);
		}
		return domain;
	}

	public List<AdminInfoDomain> getAdmininfoList()
			throws ApplicationException, SystemException {

		List<AdminInfoDomain> listDomain = new ArrayList<AdminInfoDomain>();
		List<Admininfo> listEntity = this.adminInfoCheckRepository
				.getAdmininfoList();
		for (Admininfo entity : listEntity) {
			AdminInfoDomain adminInfoDomain = new AdminInfoDomain();
			ConvertUtil.entity2domain(entity, adminInfoDomain);
			listDomain.add(adminInfoDomain);
		}
		ListUtil.clonePagedInfo(listDomain, listEntity);
		return listDomain;

	}

	public void saveAdmininfo(AdminInfoDomain domain)
			throws ApplicationException, SystemException {

		Admininfo entity = new Admininfo();
		entity = this.adminInfoCheckRepository.getAdmininfoList().get(0);
		entity.setPassword(domain.getPassword());
		entity.setName(domain.getName());
		adminInfoCheckRepository.saveAdmininfo(entity);

	}

	public void insertAdmininfo(AdminInfoDomain domain)
			throws ApplicationException, SystemException {
		
		Admininfo entity = new Admininfo();
		if (ConvertUtil.domain2entity(domain, entity)) {
			throw new ApplicationException();
		}
		adminInfoCheckRepository.saveAdmininfo(entity);

	}

	public PagedList<AdminInfoDomain> getAdmininfoPagedList(
			EntityFilter filter, Integer pageNo, Integer pageSize)
			throws ApplicationException, SystemException {
		PagedList<AdminInfoDomain> listDomain = new PagedList<AdminInfoDomain>();
		PagedList<Admininfo> listEntity = this.adminInfoCheckRepository
				.getAdmininfoPagedList(filter, pageNo, pageSize);
		for (Admininfo entity : listEntity) {
			AdminInfoDomain sfDomain = new AdminInfoDomain();
			ConvertUtil.entity2domain(entity, sfDomain);
			listDomain.add(sfDomain);
		}
		ListUtil.clonePagedInfo(listDomain, listEntity);
		return listDomain;
	}

	public void updateAdminInfo(AdminInfoDomain domain) throws SystemException,
			ApplicationException {
		if (!StringUtil.isLong(domain.getId())) {
		}
		Admininfo entity = this.adminInfoCheckRepository.getAdminInfo(Long
				.parseLong(domain.getId()));
		if (ConvertUtil.domain2entity(domain, entity)) {
		}
		adminInfoCheckRepository.saveAdmininfo(entity);
	}

	public Admininfo isAdminInputExist(AdminInfoDomain domain)
			throws SystemException, ApplicationException {
		Admininfo entity = new Admininfo();
		ConvertUtil.domain2entity(domain, entity);
		Admininfo entity2 = new Admininfo();
		entity2 = adminInfoCheckRepository.isAdminInputExist(entity);
		return entity2;

	}

}

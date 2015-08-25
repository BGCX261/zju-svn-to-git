/**
 * AdminSourceManageService.java        2009-11-25 下午11:15:16
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
import com.jsict.jszju.domain.ArticleContentDomain;
import com.jsict.jszju.domain.PaychargeDomain;
import com.jsict.jszju.entity.Article;
import com.jsict.jszju.entity.Paycharge;
import com.jsict.jszju.repository.IAdminSourceManageRepository;
import com.jsict.jszju.service.IAdminSourceManageService;
import com.jsict.jszju.service.IArticleContentService;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */

@Transactional
public class AdminSourceManageService extends BaseService implements
		IAdminSourceManageService {
	private IAdminSourceManageRepository adminSourceManageRepository;

	@Required
	public void setAdminSourceManageRepository(
			IAdminSourceManageRepository adminSourceManageRepository) {
		this.adminSourceManageRepository = adminSourceManageRepository;
	}

	public void addPaycharge(PaychargeDomain domain) throws SystemException,
			ApplicationException {
		Paycharge entity = new Paycharge();
		entity.setName(domain.getName());
		entity.setMoney(domain.getMoney());
	//	if (ConvertUtil.domain2entity(domain, entity)) {
		//}
		entity = this.adminSourceManageRepository.savePaycharge(entity);
	}

	public PagedList<PaychargeDomain> getPaychargePagedList(
			EntityFilter filter, Integer pageNo, Integer pageSize)
			throws ApplicationException, SystemException {
		PagedList<PaychargeDomain> listDomain = new PagedList<PaychargeDomain>();
		PagedList<Paycharge> listEntity = this.adminSourceManageRepository
				.getPaychargePagedList(filter, pageNo, pageSize);
		for (Paycharge entity : listEntity) {
			PaychargeDomain paychargeDomain = new PaychargeDomain();
			ConvertUtil.entity2domain(entity, paychargeDomain);
			listDomain.add(paychargeDomain);
		}
		ListUtil.clonePagedInfo(listDomain, listEntity);
		return listDomain;
	}

	public void updatePaycharge(PaychargeDomain domain) throws SystemException,
			ApplicationException {
		if (!StringUtil.isLong(domain.getId())) {
		}
		Paycharge entity=new Paycharge();
		entity = this.adminSourceManageRepository.getPaycharge(Long
				.parseLong(domain.getId()));
		if (ConvertUtil.domain2entity(domain, entity)) {
		}
		adminSourceManageRepository.savePaycharge(entity);
	}

	public PaychargeDomain getPaycharge(String id)
			throws ApplicationException, SystemException {
		PaychargeDomain domain = new PaychargeDomain();
		Paycharge entity = new Paycharge();
		if (StringUtil.isLong(id)) {
			entity = adminSourceManageRepository.getPaycharge(Long
					.parseLong(id));
			ConvertUtil.entity2domain(entity, domain);
		}
		return domain;
	}

}

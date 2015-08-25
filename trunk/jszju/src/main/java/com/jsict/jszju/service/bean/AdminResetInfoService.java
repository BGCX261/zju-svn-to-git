/**
 * AdminResetInfoService.java        2009-11-15 下午06:42:57
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.service.bean;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.jsict.base.BaseService;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.ConvertUtil;
import com.jsict.base.util.StringUtil;
import com.jsict.jszju.domain.AdminInfoDomain;
import com.jsict.jszju.domain.AdminInfoResetDomain;
import com.jsict.jszju.entity.Adminrestinfo;
import com.jsict.jszju.repository.IAdminResetInfoRepository;
import com.jsict.jszju.service.IAdminResetInfoService;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */

@Transactional
public class AdminResetInfoService extends BaseService implements
		IAdminResetInfoService {
	
	private IAdminResetInfoRepository adminResetInfoRepository;
	
	@Required
	public void setAdminResetInfoRepository(
			IAdminResetInfoRepository adminResetInfoRepository) {
		this.adminResetInfoRepository = adminResetInfoRepository;
	}

	/**
	 * <p>Description: The resetAdminInfo</p>
	 * @param domain
	 * @throws ApplicationException
	 * @throws SystemException
	 * @author: caie
	 * @update: [updatedate] [changer][change description]
	 */

	public void resetAdminInfo(AdminInfoResetDomain domain)
			throws ApplicationException, SystemException {
		if (!StringUtil.isLong(domain.getId())) {
		}
		Adminrestinfo entity = this.adminResetInfoRepository
				.getAdminResetInfo(1);
		entity.setPassword(domain.getNewpassword1());
		adminResetInfoRepository.saveAdminResetInfo(entity);

	}


}

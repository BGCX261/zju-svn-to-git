/**
 * AdminResetInfoRepository.java        2009-11-15 下午06:57:49
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.repository.bean;

import com.jsict.base.BaseRepository;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.dao.IAdminResetInfoDao;
import com.jsict.jszju.entity.Adminrestinfo;
import com.jsict.jszju.repository.IAdminResetInfoRepository;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class AdminResetInfoRepository extends BaseRepository implements
		IAdminResetInfoRepository {
	
	private IAdminResetInfoDao adminResetInfoDao;
	
	public void setAdminResetInfoDao(IAdminResetInfoDao adminResetInfoDao) {
		this.adminResetInfoDao = adminResetInfoDao;
	}

	public Adminrestinfo getAdminResetInfo(Integer id)
			throws ApplicationException, SystemException {
		
		return (Adminrestinfo)adminResetInfoDao.get(id);
	}

	public Adminrestinfo saveAdminResetInfo(Adminrestinfo adminrestinfo) {
		
		return (Adminrestinfo)adminResetInfoDao.save(adminrestinfo);
	}



}

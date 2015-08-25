/**
 * AdminSourceManageRepository.java        2009-11-25 下午11:16:00
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.repository.bean;

import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.BaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.jszju.dao.IAdminSourceManageDao;
import com.jsict.jszju.entity.Paycharge;
import com.jsict.jszju.repository.IAdminSourceManageRepository;
import com.jsict.jszju.repository.IArticleContentRepository;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class AdminSourceManageRepository extends BaseRepository implements
		IAdminSourceManageRepository {
	
	private IAdminSourceManageDao adminSourceManageDao;
	@Required
	public void setAdminSourceManageDao(
			IAdminSourceManageDao adminSourceManageDao) {
		this.adminSourceManageDao = adminSourceManageDao;
	}

	public Paycharge getPaycharge(Long id) {
		
		Integer id1=Integer.parseInt(String.valueOf(id));
		return adminSourceManageDao.get(id1);
	}

	public PagedList<Paycharge> getPaychargePagedList(EntityFilter tf,
			Integer pageNo, Integer pageSize) {
		return adminSourceManageDao.getPagedList(tf, pageNo, pageSize);
	}

	public Paycharge savePaycharge(Paycharge paycharge) {

		return adminSourceManageDao.save(paycharge);
	}


}

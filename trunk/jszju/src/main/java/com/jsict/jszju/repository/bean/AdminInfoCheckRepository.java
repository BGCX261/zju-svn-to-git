/**
 * AdminInfoCheckRepository.java        2009-11-13 下午09:11:52
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.repository.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.BaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.dao.IAdminInfoCheckDao;
import com.jsict.jszju.domain.AdminInfoDomain;
import com.jsict.jszju.entity.Admininfo;
import com.jsict.jszju.entity.Adminrestinfo;
import com.jsict.jszju.repository.IAdminInfoCheckRepository;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class AdminInfoCheckRepository extends BaseRepository implements
		IAdminInfoCheckRepository {

	private IAdminInfoCheckDao adminInfoCheckDao;

	@Required
	public void setAdminInfoCheckDao(IAdminInfoCheckDao adminInfoCheckDao) {
		this.adminInfoCheckDao = adminInfoCheckDao;
	}

	/**
	 * <p>
	 * Description: The getAdminInfo
	 * </p>
	 * 
	 * @return
	 * @throws ApplicationException
	 * @throws SystemException
	 * @author: caie
	 * @update: [updatedate] [changer][change description]
	 */

	public Admininfo getAdminInfo(Long id) {

		Integer id1 = Integer.parseInt(String.valueOf(id));

		return (Admininfo) adminInfoCheckDao.get(id1);
	}

	public List<Admininfo> getAdmininfoList() throws ApplicationException,
			SystemException {
		EntityFilter tf = new EntityFilter();
		return adminInfoCheckDao.getFilteredList(tf);
	}
	

	public void saveAdmininfo(Admininfo entity) throws ApplicationException,
			SystemException {
		
		adminInfoCheckDao.save(entity);	
	}
	
	public PagedList<Admininfo> getAdmininfoPagedList(EntityFilter tf,
			Integer pageNo, Integer pageSize) {
		
		return adminInfoCheckDao.getPagedList(tf, pageNo, pageSize);
	}
	

	public Admininfo isAdminInputExist(Admininfo entity) throws SystemException,
			ApplicationException {
		
		return adminInfoCheckDao.isAdminInputExist(entity);
	}


}

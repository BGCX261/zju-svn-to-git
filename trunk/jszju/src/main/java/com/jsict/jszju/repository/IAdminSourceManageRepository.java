/**
 * IAdminSourceManageRepository.java        2009-11-25 下午11:15:49
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.repository;

import com.jsict.base.IBaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.jszju.entity.Paycharge;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public interface IAdminSourceManageRepository extends IBaseRepository {
	
	public PagedList<Paycharge> getPaychargePagedList(EntityFilter tf,
            Integer pageNo, Integer pageSize);
	
	public Paycharge getPaycharge(Long id);
	
	public Paycharge savePaycharge(Paycharge paycharge);

}

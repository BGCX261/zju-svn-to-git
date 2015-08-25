/**
 * AdminInfoCheckDao.java        2009-11-13 下午09:57:38
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.dao.bean;

import java.util.ArrayList;
import java.util.List;

import com.jsict.base.dao.BaseDao;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Op;
import com.jsict.jszju.dao.IAdminInfoCheckDao;
import com.jsict.jszju.entity.Admininfo;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class AdminInfoCheckDao extends BaseDao<Admininfo> implements
		IAdminInfoCheckDao {

	public Admininfo isAdminInputExist(Admininfo entity) throws SystemException,
			ApplicationException {
		List<Admininfo> admininfoList = new ArrayList<Admininfo>();
		String query = "from Admininfo as admininfo";
		EntityFilter filter = new EntityFilter();
		filter.addFilter("admininfo.name", Op.EQUAL,entity.getName());
		admininfoList= executeQuery(query, filter);
		for(Admininfo aentity:admininfoList)
		{
			if(entity.getPassword().equals(aentity.getPassword())&&entity.getName().equals(aentity.getName()))
			{
				return	aentity;
			}
		}
		return null;
	}

}

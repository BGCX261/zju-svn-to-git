/**
 * UserInfoDao.java        2009-10-14 下午05:12:46
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
import com.jsict.jszju.dao.IUserInfoDao;
import com.jsict.jszju.entity.Userinfo;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">蔡锷</a>
 * @version 1.0
 */
public class UserInfoDao extends BaseDao<Userinfo> implements IUserInfoDao {

    @SuppressWarnings("unchecked")
	public Integer getUserInfoId(String username) throws SystemException,
			ApplicationException {
		List<Userinfo> userinfoList = new ArrayList<Userinfo>();
		String query = "from Userinfo as userinfo";
		EntityFilter filter = new EntityFilter();
		filter.addFilter("userinfo.name", Op.EQUAL,username);
		userinfoList= executeQuery(query, filter);
		
		return userinfoList.get(0).getId();
	}
	
	

}

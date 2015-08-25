/**
 * UserLoginCheckDao.java        2009-11-21 下午03:34:31
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.dao.bean;

import java.util.ArrayList;
import java.util.List;

import com.jsict.base.context.ProjectContext;
import com.jsict.base.dao.BaseDao;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.MessageInfo;
import com.jsict.base.util.Op;
import com.jsict.jszju.dao.IUserLoginCheckDao;
import com.jsict.jszju.entity.ItcastUser;
import com.jsict.jszju.entity.Userinfo;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class UserLoginCheckDao extends BaseDao<Userinfo> implements
		IUserLoginCheckDao {

	@SuppressWarnings("unchecked")
	public boolean checkUserInfo(Userinfo entity) throws SystemException,
			ApplicationException {
		List<Userinfo> userinfoList = new ArrayList<Userinfo>();
		String query = "from Userinfo as userinfo";
		EntityFilter filter = new EntityFilter();
		filter.addFilter("userinfo.name", Op.EQUAL,entity.getName());
		userinfoList= executeQuery(query, filter);
		for(Userinfo userentity:userinfoList)
		{
			if(entity.getPassword().equals(userentity.getPassword()))
			{
				return	true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public boolean isUserNameExist(Userinfo entity) throws SystemException,
			ApplicationException {
		List<Userinfo> userinfoList = new ArrayList<Userinfo>();
		String query = "from Userinfo as userinfo";
		EntityFilter filter = new EntityFilter();
		filter.addFilter("userinfo.name", Op.EQUAL,entity.getName());
		userinfoList= executeQuery(query, filter);
		for(Userinfo userentity:userinfoList)
		{
			if(entity.getName().equals(userentity.getName()))
			{
				MessageInfo messageInfo = new MessageInfo("001");
				ProjectContext.getErrorList().add(messageInfo);
				return	false;
			}
		}
		this.save(entity);
		return true;
	}
	

}

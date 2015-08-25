/**
 * 
 */
package com.jsict.jszju.dao.bean;

import java.util.ArrayList;
import java.util.List;

import com.jsict.base.dao.BaseDao;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Op;
import com.jsict.jszju.dao.IItcastUserDao;
import com.jsict.jszju.entity.ItcastUser;
import com.jsict.jszju.entity.Userinfo;

/**
 * @author Administrator
 *
 */
public class ItcastUserDao extends BaseDao<ItcastUser> implements IItcastUserDao {
	
	@SuppressWarnings("unchecked")
	public boolean checkUserInfo(ItcastUser entity) throws SystemException,
			ApplicationException {
		List<ItcastUser> userinfoList = new ArrayList<ItcastUser>();
		String query = "from ItcastUser as userinfo";
		EntityFilter filter = new EntityFilter();
		filter.addFilter("userinfo.loginName", Op.EQUAL,entity.getLoginName());
		userinfoList= executeQuery(query, filter);
		for(ItcastUser userentity:userinfoList)
		{
			if(entity.getPassword().equals(userentity.getPassword()))
			{
				return	true;
			}
		}
		return false;
	}


}

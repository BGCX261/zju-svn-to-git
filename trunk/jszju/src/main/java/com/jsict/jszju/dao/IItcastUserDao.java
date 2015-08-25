/**
 * 
 */
package com.jsict.jszju.dao;

import com.jsict.base.dao.IBaseDao;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.entity.ItcastUser;

/**
 * @author Administrator
 *
 */
public interface IItcastUserDao extends IBaseDao<ItcastUser> {
	
	public boolean checkUserInfo(ItcastUser entity) throws SystemException,
    ApplicationException;

}

/**
 * UserRoleDao.java 2008-11-10 下午03:17:54 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dao.bean;

import java.io.Serializable;

import com.jsict.base.dao.BaseDao;
import com.jsict.platform.dao.IUserRoleDao;
import com.jsict.platform.entity.UserRole;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class UserRoleDao extends BaseDao<UserRole> implements IUserRoleDao
{
    public final static String GET_USER_ROLE = "from UserRole where useraccount.id = ? and role.id = ?";

    public final static String DELETE_FOR_USER = "delete from UserRole where useraccount.id = ?";

    public final static String DELETEROLE_FOR_USER = "delete from UserRole where useraccount.id = ? and role.id=?";

    public UserRole getUserRole(Serializable userId, Serializable roleId)
    {
        return getFirst(executeQuery(GET_USER_ROLE, userId, roleId));
    }

    public void deleteForUser(Serializable userId)
    {
        executeUpdate(DELETE_FOR_USER, userId);
    }

    public void deleteForUser(Serializable userId, Serializable roleId)
    {
        executeUpdate(DELETEROLE_FOR_USER, Long.parseLong(String
                .valueOf(userId)), Long.parseLong(String.valueOf(roleId)));
    }
}

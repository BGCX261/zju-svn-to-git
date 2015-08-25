/**
 * IRoleDao.java 2008-11-10 下午03:06:57 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dao;

import java.io.Serializable;
import java.util.List;

import com.jsict.base.dao.IBaseDao;
import com.jsict.platform.entity.Role;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public interface IRoleDao extends IBaseDao<Role>
{

    /**
     * Gets the roles by user.
     * 
     * @param userId
     *            the user id
     * 
     * @return the roles by user
     */
    public List<Role> getRolesByUser(Serializable userId);

    public Role getByName(String roleName);

    Role getByBCode(String code);

    List<Role> getAssignableRole(Serializable subsystemId, Serializable roleId);

}

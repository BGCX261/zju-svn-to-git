/**
 * IRolePermissionDao.java 2008-11-10 下午03:06:57 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dao;

import java.io.Serializable;

import com.jsict.base.dao.IBaseDao;
import com.jsict.platform.entity.RolePermission;

;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>.
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public interface IRolePermissionDao extends IBaseDao<RolePermission>
{

    /**
     * Gets the role permission.
     * 
     * @param roleId
     *            the role id
     * @param permissionId
     *            the permission id
     * 
     * @return the role permission
     */
    public RolePermission getRolePermission(Serializable roleId,
            Serializable permissionId);

    /**
     * <p>
     * Description: The Delete for role.
     * </p>
     * 
     * @param roleId
     *            the role id
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    public void deleteForRoleAndPlatform(String platform, Serializable roleId);
}

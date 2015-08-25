/**
 * RolePermissionDao.java 2008-11-10 下午03:16:42 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dao.bean;

import java.io.Serializable;

import com.jsict.base.dao.BaseDao;
import com.jsict.base.dao.EntityFilter;
import com.jsict.platform.dao.IRolePermissionDao;
import com.jsict.platform.entity.RolePermission;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class RolePermissionDao extends BaseDao<RolePermission> implements
        IRolePermissionDao
{
    public final static String GET_ROLE_PERMISSION = "from RolePermission where role.id = ? and permission.id = ?";

    public final static String DELETE_FOR_ROLE = "delete from RolePermission rp where rp.role.id = ? and rp.permission in (from Permission where platform = ?)  ";

    public RolePermission getRolePermission(Serializable roleId,
            Serializable permissionId)
    {
        return getFirst(executeQuery(GET_ROLE_PERMISSION, roleId, permissionId));
    }

    public void deleteForRoleAndPlatform(String platform, Serializable roleId)
    {
        executeUpdate(DELETE_FOR_ROLE, roleId, platform);
    }
}

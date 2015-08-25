/**
 * AssignableRoleDao.java 2009-1-7 下午09:46:16 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dao.bean;

import java.io.Serializable;
import java.util.List;

import com.jsict.base.dao.BaseDao;
import com.jsict.platform.dao.IAssignableRoleDao;
import com.jsict.platform.entity.AssignableRole;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class AssignableRoleDao extends BaseDao<AssignableRole> implements
        IAssignableRoleDao
{
    private static final String SQL_GET_ASSIGNABLE_ROLE = "select distinct ar.roleByAssignableRoleId "
            + "from AssignableRole ar,RolePermission rp where rp in elements(ar.roleByAssignableRoleId.rolePermissions) "
            + "and rp.permission.subsystem=? and ar.roleByRoleId.id=?";

    public List<AssignableRole> getAssignableRole(Serializable subsystemId,
            Serializable roleId)
    {
        return super.executeQuery(SQL_GET_ASSIGNABLE_ROLE, String
                .valueOf(subsystemId), Long.valueOf(String.valueOf(roleId)));
    }
}

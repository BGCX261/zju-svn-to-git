/**
 * RoleDao.java 2008-11-10 下午03:16:11 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dao.bean;

import java.io.Serializable;
import java.util.List;

import com.jsict.base.dao.BaseDao;
import com.jsict.platform.dao.IRoleDao;
import com.jsict.platform.entity.Role;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class RoleDao extends BaseDao<Role> implements IRoleDao
{
    public final static String GET_ROLES_BY_USER = "select r from Role r join r.userRoles ur where ur.useraccount.id = ?";

    private final static String GET_ROLE_BY_NAME = "from Role r where r.name=?";

    private final static String GET_ROLE_BY_BCODE = "from Role r where r.BCode=?";

    private static final String SQL_GET_ASSIGNABLE_ROLE = "select distinct ar.roleByAssignableRoleId "
            + "from AssignableRole ar,RolePermission rp where rp in elements(ar.roleByAssignableRoleId.rolePermissions) "
            + "and rp.permission.subsystem.id=? and ar.roleByRoleId.id=?";

    public List<Role> getRolesByUser(Serializable userId)
    {
        return executeQuery(GET_ROLES_BY_USER, userId);
    }

    public Role getByName(String roleName)
    {
        // TODO Auto-generated method stub
        List<Role> list = executeQuery(GET_ROLE_BY_NAME, roleName);
        if(list.size() == 0)
            return null;
        return list.get(0);
    }

    public Role getByBCode(String bCode)
    {
        // TODO Auto-generated method stub
        List<Role> list = executeQuery(GET_ROLE_BY_BCODE, bCode);
        if(list.size() == 0)
            return null;
        return list.get(0);
    }

    public List<Role> getAssignableRole(Serializable subsystemId,
            Serializable roleId)
    {
        return executeQuery(SQL_GET_ASSIGNABLE_ROLE, String
                .valueOf(subsystemId), Long.valueOf(String.valueOf(roleId)));
    }
}

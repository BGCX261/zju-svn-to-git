/**
 * PermissionDao.java 2008-11-10 下午03:15:46 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dao.bean;

import java.io.Serializable;
import java.util.List;

import com.jsict.base.dao.BaseDao;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.util.Op;
import com.jsict.base.util.StringUtil;
import com.jsict.platform.dao.IPermissionDao;
import com.jsict.platform.entity.Permission;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class PermissionDao extends BaseDao<Permission> implements
        IPermissionDao
{
    public final static String GET_PERMISSIONS_BY_ROLE = "select p from Permission ";

    public final static String GET_PERMISSIONS_BY_USER = "select p from Permission  ";

    public final static String GET_PERMISSIONS_BY_TYPE = "select p from Permission ";

    public List<Permission> getPermissionListByRole(Serializable roleId)
    {
        return executeQuery(GET_PERMISSIONS_BY_ROLE, roleId);
    }

    public List<Permission> getPermissionListByUser(Serializable userId)
    {
        return executeQuery(GET_PERMISSIONS_BY_USER, userId);
    }

    public List<Permission> getPermissionListByType(String... types)
    {
        return executeQuery(GET_PERMISSIONS_BY_TYPE, types);
    }

    @SuppressWarnings("unchecked")
    private List<Permission> getPermissionsWithJoin(Serializable parentId,
             String platform, 
            boolean onlyRoot, String... types)
    {
        String ql = "from Permission p ";

        EntityFilter entityFilter = new EntityFilter();

        if(types != null && types.length > 0)
        {
            entityFilter.addFilter("p.type", Op.IN, types);
        }

        if(onlyRoot)
        {
            entityFilter.addFilter("p.parentPermission is null");
        }
        else
        {
            if(parentId != null)
            {
                entityFilter.addFilter("p.parentPermission.id", Op.EQUAL,
                    parentId);
            }
        }


        return executeQuery(ql, entityFilter);
    }

    public List<Permission> getPermissionsByUser(Serializable parentId,
            String platform, boolean onlyRoot,
            String... types)
    {
        return this.getPermissionsWithJoin(parentId,  platform, 
            onlyRoot, types);
    }

    public List<Permission> getPermissionsByRoleAndPlatform(
            Serializable roleId, String platform)
    {
        return this.getPermissionsWithJoin(null,  platform,  false,
            null);
    }

    @SuppressWarnings("unchecked")
    public List<Permission> getPermissions(Serializable parentId,
            String platform, String... types)
    {
        String ql = "from Permission p ";

        EntityFilter entityFilter = new EntityFilter();

        if(types != null && types.length > 0)
        {
            entityFilter.addFilter("p.type", Op.IN, types);
        }

        if(parentId != null)
        {
            entityFilter.addFilter("p.parentPermission.id", Op.EQUAL, parentId);
        }
        else
        {
            entityFilter.addFilter("p.parentPermission is null");
        }

        if(!StringUtil.isNullString(platform))
        {
            entityFilter.addFilter("p.subsystem.id", Op.EQUAL, platform);
        }

        entityFilter.addOrder("p.sequence");
        return executeQuery(ql, entityFilter);
    }
}

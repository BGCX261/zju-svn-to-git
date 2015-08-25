/**
 * IPermissionDao.java 2008-11-10 下午03:06:57 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dao;

import java.io.Serializable;
import java.util.List;

import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.IBaseDao;
import com.jsict.base.util.Op;
import com.jsict.base.util.StringUtil;
import com.jsict.platform.entity.Permission;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>.
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public interface IPermissionDao extends IBaseDao<Permission>
{

    /**
     * Gets the permission list by user.
     * 
     * @param userId
     *            the user id
     * 
     * @return the permission list by user
     */
    List<Permission> getPermissionListByUser(Serializable userId);

    /**
     * Gets the permission list by role.
     * 
     * @param roleId
     *            the role id
     * 
     * @return the permission list by role
     */
    List<Permission> getPermissionListByRole(Serializable roleId);

    /**
     * Gets the permission list by type.
     * 
     * @param types
     *            the types
     * 
     * @return the permission list by type
     */
    public List<Permission> getPermissionListByType(String... types);

    /**
     * Gets the root menu permissions.
     * 
     * @return the root menu permissions
     */
    public List<Permission> getPermissionsByUser(Serializable parentId,
             String platform, boolean onlyRoot,
            String... types);

    /**
     * Gets the permissions.
     * 
     * @param parentId
     *            the parent id
     * @param platform
     *            the platform
     * @param types
     *            the types
     * 
     * @return the permissions
     */
    @SuppressWarnings("unchecked")
    public List<Permission> getPermissions(Serializable parentId,
            String platform, String... types);

    /**
     * Gets the permissions by role and platform.
     * 
     * @param roleId
     *            the role id
     * @param platform
     *            the platform
     * 
     * @return the permissions by role and platform
     */
    public List<Permission> getPermissionsByRoleAndPlatform(
            Serializable roleId, String platform);

}

/**
 * IPermissionRepository.java 2008-11-10 下午03:06:57 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.repository;

import java.io.Serializable;
import java.util.List;

import com.jsict.base.IBaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.platform.constants.CodeKey;
import com.jsict.platform.entity.Permission;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>.
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public interface IPermissionRepository extends IBaseRepository
{

    /**
     * <p>
     * Description: The Permission.
     * </p>
     * 
     * @param permission
     *            the permission
     * 
     * @return the permission
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    Permission save(Permission permission);

    /**
     * <p>
     * Description: The Delete.
     * </p>
     * 
     * @param permission
     *            the permission
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    void delete(Permission permission);

    /**
     * <p>
     * Description: The Permission.
     * </p>
     * 
     * @param permissionId
     *            the permission id
     * 
     * @return the permission
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    Permission get(Serializable permissionId);

    /**
     * Gets the permission list.
     * 
     * @param entityFilter
     *            the entity filter
     * @param pageNo
     *            the page no
     * @param pageSize
     *            the page size
     * 
     * @return the permission list
     */
    PagedList<Permission> getPermissionList(EntityFilter entityFilter,
            Integer pageNo, Integer pageSize);

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
     * Gets the permissions for root menu.
     * 
     * @param userId
     *            the user id
     * @param platform
     *            the platform
     * 
     * @return the permissions for root menu
     */
    public List<Permission> getPermissionsForRootMenu(String platform);

    /**
     * Gets the permission for sub menu.
     * 
     * @param parentId
     *            the parent id
     * @param userId
     *            the user id
     * @param platform
     *            the platform
     * 
     * @return the permission for sub menu
     */
    public List<Permission> getPermissionsForSubMenu(Serializable parentId,
             String platform);

    /**
     * Gets the all permissions.
     * 
     * @return the all permissions
     */
    public List<Permission> getAllPermissions();

    /**
     * Gets the root permissions for platform.
     * 
     * @param platform
     *            the platform
     * 
     * @return the root permissions for platform
     */
    public List<Permission> getRootPermissionsForPlatform(String platform);

    /**
     * Gets the permissions by platform and role.
     * 
     * @param parentId
     *            the parent id
     * @param platform
     *            the platform
     * @param roleId
     *            the role id
     * 
     * @return the permissions by platform and role
     */
    public List<Permission> getPermissionsByPlatformAndRole(String platform,
            Serializable roleId);
}

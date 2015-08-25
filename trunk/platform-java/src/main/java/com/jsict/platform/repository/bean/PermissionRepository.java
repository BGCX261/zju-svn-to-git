/**
 * IPermissionRepository.java 2008-11-10 下午03:06:57 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.repository.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.BaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.platform.constants.CodeKey;
import com.jsict.platform.dao.IPermissionDao;
import com.jsict.platform.entity.Permission;
import com.jsict.platform.repository.IPermissionRepository;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class PermissionRepository extends BaseRepository implements
        IPermissionRepository
{
    public IPermissionDao permissionDao;

    public void delete(Permission permission)
    {
        permissionDao.delete(permission);

    }

    public Permission get(Serializable permissionId)
    {
        return permissionDao.get(permissionId);
    }

    public PagedList<Permission> getPermissionList(EntityFilter entityFilter,
            Integer pageNo, Integer pageSize)
    {
        return permissionDao.getPagedList(entityFilter, pageNo, pageSize);
    }

    public Permission save(Permission permission)
    {
        return permissionDao.save(permission);
    }

    public List<Permission> getPermissionListByRole(Serializable roleId)
    {
        return permissionDao.getPermissionListByRole(roleId);
    }

    public List<Permission> getPermissionListByUser(Serializable userId)
    {
        return permissionDao.getPermissionListByUser(userId);
    }

    public List<Permission> getRootPermissionsForPlatform(String platform)
    {
        return permissionDao.getPermissions(null, platform,
            CodeKey.PERMISSION_TYPE_MENU, CodeKey.PERMISSION_TYPE_ITEM);
    }

    public List<Permission> getPermissionsForSubMenu(Serializable parentId,
            String platform)
    {
        assert parentId != null;
        return permissionDao.getPermissionsByUser(parentId,  platform,
            false, CodeKey.PERMISSION_TYPE_MENU);
    }

    public List<Permission> getPermissionsForRootMenu(
            String platform)
    {
        return permissionDao.getPermissionsByUser(null, platform, true,
            CodeKey.PERMISSION_TYPE_MENU);
    }

    public List<Permission> getAllPermissions()
    {
        return permissionDao.getAllList();
    }

    public List<Permission> getPermissionsByPlatformAndRole(String platform,
            Serializable roleId)
    {
        assert roleId != null;

        return permissionDao.getPermissionsByRoleAndPlatform(roleId, platform);
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param permissionDao
     *            The permissionDao to set.
     */
    @Required
    public void setPermissionDao(IPermissionDao permissionDao)
    {
        this.permissionDao = permissionDao;
    }
}

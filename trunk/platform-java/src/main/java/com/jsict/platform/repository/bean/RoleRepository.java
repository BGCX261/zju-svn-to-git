/**
 * IRoleRepository.java 2008-11-10 下午03:06:57 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.repository.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.BaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.platform.dao.IAssignableRoleDao;
import com.jsict.platform.dao.IRoleDao;
import com.jsict.platform.dao.IRolePermissionDao;
import com.jsict.platform.dao.IUserAccountDao;
import com.jsict.platform.dao.IUserRoleDao;
import com.jsict.platform.entity.AssignableRole;
import com.jsict.platform.entity.Permission;
import com.jsict.platform.entity.Role;
import com.jsict.platform.entity.RolePermission;
import com.jsict.platform.entity.UserAccount;
import com.jsict.platform.entity.UserRole;
import com.jsict.platform.repository.IRoleRepository;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class RoleRepository extends BaseRepository implements IRoleRepository
{
    public IRoleDao roleDao;

    public IRolePermissionDao rolePermissionDao;

    public IUserRoleDao userRoleDao;

    private IAssignableRoleDao assignableRoleDao;

    private IUserAccountDao userAccountDao;

    public void addPermissionToRole(Permission permission, Role role)
    {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPermission(permission);
        rolePermission.setRole(role);

        rolePermissionDao.save(rolePermission);

    }

    public void addUserToRole(UserAccount useraccount, Role role)
    {
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUseraccount(useraccount);

        userRoleDao.save(userRole);

    }

    public void delete(Role role)
    {
        roleDao.delete(role);

    }

    public PagedList<Role> getRoleList(EntityFilter entityFilter,
            Integer pageNo, Integer pageSize)
    {
        return roleDao.getPagedList(entityFilter, pageNo, pageSize);
    }

    public List<Role> getRoleListBySubsystem(Serializable roleId,
            Serializable subsystemId)
    {

        return roleDao.getAssignableRole(subsystemId, roleId);
    }

    public Role save(Role role)
    {
        return roleDao.save(role);
    }

    public Role get(Serializable id)
    {
        return roleDao.get(Long.parseLong(String.valueOf(id)));
    }

    public Role getByName(String roleName)
    {
        return roleDao.getByName(roleName);
    }

    public Role getByBCode(String bCode)
    {
        // TODO Auto-generated method stub
        return roleDao.getByBCode(bCode);
    }

    public List<Role> getRolesByUser(UserAccount userAccount)
    {
        return roleDao.getRolesByUser(userAccount.getId());
    }

    public List<Role> getAllRoleList()
    {
        return roleDao.getAllList();
    }

    public void detachPermissionsFromRole(String platform, Role role)
    {
        assert role != null;
        rolePermissionDao.deleteForRoleAndPlatform(platform, role.getId());
    }

    public boolean canAssignRoleByUserId(Serializable userId,
            Serializable assignRoleId)
    {
        UserAccount userAccount = userAccountDao.get(Long.parseLong(String
                .valueOf(userId)));
        Set<UserRole> userRoles = userAccount.getUserRoles();
        for (UserRole userRole : userRoles)
        {
            if(canAssignRoleByRoleId(userRole.getRole().getId(), assignRoleId))
            {
                return true;
            }
        }
        return false;
    }

    public boolean canAssignRoleByRoleId(Serializable roleId,
            Serializable assignRoleId)
    {
        EntityFilter ef = new EntityFilter();
        ef.addFilter("roleByRoleId.id", Long.valueOf(String.valueOf(roleId)));
        ef.addFilter("roleByAssignableRoleId.id", Long.valueOf(String
                .valueOf(assignRoleId)));

        PagedList<AssignableRole> ars = assignableRoleDao.getFilteredList(ef);
        return ars.size() > 0;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param roleDao
     *            The roleDao to set.
     */
    @Required
    public void setRoleDao(IRoleDao roleDao)
    {
        this.roleDao = roleDao;
    }

    @Required
    public void setRolePermissionDao(IRolePermissionDao rolePermissionDao)
    {
        this.rolePermissionDao = rolePermissionDao;
    }

    @Required
    public void setUserRoleDao(IUserRoleDao userRoleDao)
    {
        this.userRoleDao = userRoleDao;
    }

    @Required
    public void setAssignableRoleDao(IAssignableRoleDao assignableRoleDao)
    {
        this.assignableRoleDao = assignableRoleDao;
    }

    @Required
    public void setUserAccountDao(IUserAccountDao userAccountDao)
    {
        this.userAccountDao = userAccountDao;
    }
}

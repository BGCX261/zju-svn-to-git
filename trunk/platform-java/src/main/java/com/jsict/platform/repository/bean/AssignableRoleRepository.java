/**
 * AssignableRoleRepository.java 2009-1-7 下午09:47:49 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.repository.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.BaseRepository;
import com.jsict.platform.dao.IAssignableRoleDao;
import com.jsict.platform.dao.IRoleDao;
import com.jsict.platform.dao.IUserAccountDao;
import com.jsict.platform.entity.AssignableRole;
import com.jsict.platform.entity.Role;
import com.jsict.platform.entity.UserAccount;
import com.jsict.platform.entity.UserRole;
import com.jsict.platform.repository.IAssignableRoleRepository;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class AssignableRoleRepository extends BaseRepository implements
        IAssignableRoleRepository
{
    private IAssignableRoleDao assignableRoleDao;

    private IUserAccountDao userAccountDao;

    private IRoleDao roleDao;

    @Required
    public void setRoleDao(IRoleDao roleDao)
    {
        this.roleDao = roleDao;
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

    public List<Role> getAssignableRoleByUserId(Serializable userId)
    {
        UserAccount userAccount = userAccountDao.get(Long.parseLong(String
                .valueOf(userId)));
        Set<UserRole> userRoles = userAccount.getUserRoles();
        List<Role> roles = new ArrayList<Role>();
        for (UserRole userRole : userRoles)
        {
            roles.addAll(getAssignableRoleByRoleId(userRole.getRole()));
        }

        return roles;
    }

    public List<Role> getAssignableRoleByRoleId(Serializable roleId)
    {
        Role role = roleDao.get(roleId);
        return getAssignableRoleByRole(role);
    }

    private List<Role> getAssignableRoleByRole(Role role)
    {
        Set<AssignableRole> assignableRoles = role
                .getAssignablePermissionsForRoleId();
        List<Role> roles = new ArrayList<Role>();
        for (AssignableRole assignableRole : assignableRoles)
        {
            roles.add(assignableRole.getRoleByAssignableRoleId());
        }
        return roles;
    }
}

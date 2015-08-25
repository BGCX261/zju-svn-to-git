/**
 * IRoleRepository.java 2008-11-10 下午03:06:57 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.repository;

import java.io.Serializable;
import java.util.List;

import com.jsict.base.IBaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.platform.entity.Permission;
import com.jsict.platform.entity.Role;
import com.jsict.platform.entity.UserAccount;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * .
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public interface IRoleRepository extends IBaseRepository
{

    /**
     * <p>
     * Description: The Adds the user to role.
     * </p>
     * 
     * @param useraccount
     *            the useraccount
     * @param role
     *            the role
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    void addUserToRole(UserAccount useraccount, Role role);

    /**
     * <p>
     * Description: The Adds the permission to role.
     * </p>
     * 
     * @param permission
     *            the permission
     * @param role
     *            the role
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    void addPermissionToRole(Permission permission, Role role);

    /**
     * <p>
     * Description: The Role.
     * </p>
     * 
     * @param role
     *            the role
     * 
     * @return the role
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    Role save(Role role);

    /**
     * <p>
     * Description: The Delete.
     * </p>
     * 
     * @param role
     *            the role
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    void delete(Role role);

    /**
     * Gets the role list.
     * 
     * @param entityFilter
     *            the entity filter
     * @param pageNo
     *            the page no
     * @param pageSize
     *            the page size
     * 
     * @return the role list
     */
    PagedList<Role> getRoleList(EntityFilter entityFilter, Integer pageNo,
            Integer pageSize);

    /**
     * <p>
     * Description: The Role.
     * </p>
     * 
     * @param id
     *            the id
     * 
     * @return the role
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    public Role get(Serializable id);

    /**
     * Gets the roles by user.
     * 
     * @param userAccount
     *            the user account
     * 
     * @return the roles by user
     */
    public List<Role> getRolesByUser(UserAccount userAccount);

    /**
     * Gets the all role list.
     * 
     * @return the all role list
     */
    public List<Role> getAllRoleList();

    /**
     * <p>
     * Description: The Detach permissions from role.
     * </p>
     * 
     * @param role
     *            the role
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    public void detachPermissionsFromRole(String platform, Role role);

    Role getByName(String roleName);

    Role getByBCode(String code);

    List<Role> getRoleListBySubsystem(Serializable roleId,
            Serializable subsystemId);

    boolean canAssignRoleByUserId(Serializable userId, Serializable assignRoleId);

    boolean canAssignRoleByRoleId(Serializable roleId, Serializable assignRoleId);
}

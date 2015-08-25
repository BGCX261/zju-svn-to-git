/**
 * @(#)IAuthorizationService.java 2008-11-12 13:22:38 Administrator 版权所有 (c)
 *                                2007-2008 江苏鸿信系统集成有限公司
 */
package com.jsict.platform.service;

import java.io.Serializable;
import java.util.List;

import com.jsict.base.IBaseService;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.platform.domain.PermissionDomain;
import com.jsict.platform.domain.RoleDomain;
import com.jsict.platform.domain.SubsystemDomain;

/**
 * <P>
 * Description: The Interface IAuthorizationService.
 * </p>
 * 
 * @author Administrator
 * @version 1.0
 */
public interface IAuthorizationService extends IBaseService
{

    /**
     * <p>
     * Description: The Update user roles.
     * </p>
     * 
     * @param userId
     *            the user id
     * @param roleIds
     *            the role ids
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    // public void updateUserRoles(Serializable userId, Serializable... roleIds);
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
     * 
     * @throws ApplicationException
     *             the application exception
     * @throws SystemException
     *             the system exception
     */
    public PagedList<RoleDomain> getRoleList(EntityFilter entityFilter,
            Integer pageNo, Integer pageSize) throws ApplicationException,
        SystemException;

    /**
     * <p>
     * Description: The Delete role.
     * </p>
     * 
     * @param roleDomain
     *            the role domain
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    public void deleteRole(RoleDomain roleDomain);

    /**
     * Gets the role.
     * 
     * @param id
     *            the id
     * 
     * @return the role
     * 
     * @throws ApplicationException
     *             the application exception
     * @throws SystemException
     *             the system exception
     */
    public RoleDomain getRoleWithPermissions(Serializable id)
        throws ApplicationException, SystemException;

    /**
     * Gets the roles by user.
     * 
     * @param userId
     *            the user id
     * 
     * @return the roles by user
     * 
     * @throws ApplicationException
     *             the application exception
     * @throws SystemException
     *             the system exception
     */
    public List<RoleDomain> getAllRolesByUserWithItemsSelected(
            Serializable userId) throws ApplicationException, SystemException;

    /**
     * <p>
     * Description: The Role domain.
     * </p>
     * 
     * @param roleDomain
     *            the role domain
     * 
     * @return the role domain
     * 
     * @throws SystemException
     *             the system exception
     * @throws ApplicationException
     *             the application exception
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    public RoleDomain saveRole(RoleDomain roleDomain) throws SystemException,
        ApplicationException;

    /**
     * <p>
     * Description: The Role domain.
     * </p>
     * 
     * @param roleDomain
     *            the role domain
     * 
     * @return the role domain
     * 
     * @throws SystemException
     *             the system exception
     * @throws ApplicationException
     *             the application exception
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    public RoleDomain updateRole(RoleDomain roleDomain) throws SystemException,
        ApplicationException;

    /**
     * Gets the root permissions for menu.
     * 
     * @return the root permissions for menu
     * 
     * @throws ApplicationException
     *             the application exception
     * @throws SystemException
     *             the system exception
     */
    public List<PermissionDomain> getRootPermissionsForCurrentUser(
            String platform) throws ApplicationException, SystemException;

    /**
     * Gets the permission by current user.
     * 
     * @return the permission by current user
     * 
     * @throws ApplicationException
     *             the application exception
     * @throws SystemException
     *             the system exception
     */
    public List<PermissionDomain> getPermissionByCurrentUser()
        throws ApplicationException, SystemException;

    /**
     * Gets the permission by role.
     * 
     * @param roleId
     *            the role id
     * 
     * @return the permission by role
     * 
     * @throws ApplicationException
     *             the application exception
     * @throws SystemException
     *             the system exception
     */
    public List<PermissionDomain> getPermissionByRole(Serializable roleId)
        throws ApplicationException, SystemException;

    /**
     * Gets the all permissions.
     * 
     * @return the all permissions
     * 
     * @throws ApplicationException
     *             the application exception
     * @throws SystemException
     *             the system exception
     */
    public List<PermissionDomain> getAllPermissions()
        throws ApplicationException, SystemException;

    /**
     * Gets the role without permissions.
     * 
     * @param id
     *            the id
     * 
     * @return the role without permissions
     * 
     * @throws ApplicationException
     *             the application exception
     * @throws SystemException
     *             the system exception
     */
    public RoleDomain getRoleWithoutPermissions(Serializable id)
        throws ApplicationException, SystemException;

    /**
     * Gets the root permissions for specific platform.
     * 
     * @param platform
     *            the platform
     * 
     * @return the root permissions for specific platform
     * 
     * @throws ApplicationException
     *             the application exception
     * @throws SystemException
     *             the system exception
     */
    public List<PermissionDomain> getRootPermissionsForSpecificPlatform(
            String platform) throws ApplicationException, SystemException;

    /**
     * <p>
     * Description: The Update permission list.
     * </p>
     * 
     * @param domains
     *            the domains
     * 
     * @throws SystemException
     *             the system exception
     * @throws ApplicationException
     *             the application exception
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    public void updatePermissionList(PermissionDomain... domains)
        throws SystemException, ApplicationException;

    /**
     * Gets the root permissions for specific platform and role.
     * 
     * @param platform
     *            the platform
     * @param roleId
     *            the role id
     * 
     * @return the root permissions for specific platform and role
     * 
     * @throws ApplicationException
     *             the application exception
     * @throws SystemException
     *             the system exception
     */
    public List<PermissionDomain> getRootPermissionsForSpecificPlatformAndRole(
            String platform, Serializable roleId) throws ApplicationException,
        SystemException;

    /**
     * <p>
     * Description: The Update role permissions.
     * </p>
     * 
     * @param roleId
     *            the role id
     * @param permissionIds
     *            the permission ids
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    public void updateRolePermissions(String platform, Serializable roleId,
            Serializable... permissionIds);

    public PermissionDomain getPermissionWithParent(Serializable permissionId)
        throws ApplicationException, SystemException;

    List<SubsystemDomain> getAllSubsystem() throws ApplicationException,
        SystemException;

    RoleDomain getRoleWithoutPermissionsByBCode(String roleName)
        throws ApplicationException, SystemException;

    List<RoleDomain> getRolesByUserWithItemsSelectedViaOp(Serializable userId,
            Serializable masterUserId, Serializable subsystemId)
        throws ApplicationException, SystemException;

    List<RoleDomain> getAssignableRolesByRoleId(Serializable roleId,
            Serializable subsystemId) throws ApplicationException,
        SystemException;

    List<RoleDomain> getAssignableRolesByUserId(Serializable userId,
            Serializable subsystemId) throws ApplicationException,
        SystemException;

    void updateParticialUserRoles(Serializable userId,
            Serializable masterUserId, Serializable subsystemId,
            Serializable[] roleIds) throws ApplicationException,
        SystemException;

    boolean canAssignedRole(Serializable roleId) throws ApplicationException,
        SystemException;
}

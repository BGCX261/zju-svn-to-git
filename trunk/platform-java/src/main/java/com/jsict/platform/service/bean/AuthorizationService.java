package com.jsict.platform.service.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.jsict.base.BaseDomain;
import com.jsict.base.BaseService;
import com.jsict.base.context.ProjectContext;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.ConvertUtil;
import com.jsict.base.util.ListUtil;
import com.jsict.platform.constants.RolesConsts;
import com.jsict.platform.domain.PermissionDomain;
import com.jsict.platform.domain.RoleDomain;
import com.jsict.platform.domain.SubsystemDomain;
import com.jsict.platform.entity.Permission;
import com.jsict.platform.entity.Role;
import com.jsict.platform.entity.RolePermission;
import com.jsict.platform.entity.Subsystem;
import com.jsict.platform.entity.UserAccount;
import com.jsict.platform.entity.UserRole;
import com.jsict.platform.repository.IPermissionRepository;
import com.jsict.platform.repository.IRoleRepository;
import com.jsict.platform.repository.ISubsystemRepository;
import com.jsict.platform.repository.IUserAccountRepository;
import com.jsict.platform.service.IAuthorizationService;
import com.jsict.platform.util.SecurityUtils;

@Transactional
public class AuthorizationService extends BaseService implements
        IAuthorizationService
{
    private IRoleRepository roleRepository;

    private IPermissionRepository permissionRepository;

    private IUserAccountRepository userAccountRepository;

    private ISubsystemRepository subsystemRepository;

    public void updateRolePermissions(String platform, Serializable roleId,
            Serializable... permissionIds)
    {
        Role role = roleRepository.get(Long.parseLong(roleId.toString()));
        roleRepository.detachPermissionsFromRole(platform, role);

        Permission permission;
        if(permissionIds != null && permissionIds.length > 0)
        {
            for (Serializable permissionId : permissionIds)
            {
                permission = permissionRepository.get(Long
                        .parseLong(permissionId.toString()));

                roleRepository.addPermissionToRole(permission, role);
            }
        }
    }

    private void updateUserRoles(Serializable userId, Serializable... roleIds)
    {
        UserAccount userAccount = userAccountRepository.getUserAccount(Long
                .parseLong(userId.toString()));
        userAccountRepository.detachRolesFromUser(userAccount);

        Role role;
        if(roleIds != null && roleIds.length > 0)
        {
            for (Serializable roleId : roleIds)
            {
                role = roleRepository.get(Long.parseLong(roleId.toString()));
                roleRepository.addUserToRole(userAccount, role);
            }
        }
    }

    /**
     * <p>
     * Description: 运营商更新被管理的用户的id
     * </p>
     * 
     * @param 被管理的用户id
     * @param 运营商用户Id
     * @param 赋予的权限
     * @author: lgq
     * @throws SystemException
     * @throws ApplicationException
     * @update: [updatedate] [changer][change description]
     */
    public void updateParticialUserRoles(Serializable userId,
            Serializable masterUserId, Serializable subsystemId,
            Serializable... roleIds) throws ApplicationException,
        SystemException
    {
        if(masterUserId == null && subsystemId == null
                || SecurityUtils.hasAdminRole())
        {
            updateUserRoles(userId, roleIds);
            return;
        }

        UserAccount userAccount = userAccountRepository.getUserAccount(Long
                .parseLong(userId.toString()));
        //first remove roles
        List<RoleDomain> roles = getAssignableRolesByUserId(masterUserId,
            subsystemId);
        for (RoleDomain roleDomain : roles)
        {
            userAccountRepository
                    .detachRoleFromUser(userId, roleDomain.getId());
        }
        //assign roles
        Role role;
        if(roleIds != null && roleIds.length > 0)
        {
            for (Serializable roleId : roleIds)
            {
                role = roleRepository.get(Long.parseLong(roleId.toString()));
                roleRepository.addUserToRole(userAccount, role);
            }
        }
    }

    public void deleteRole(RoleDomain roleDomain)
    {
        roleRepository.delete(roleRepository.get(Long.parseLong(roleDomain
                .getId())));
    }

    public PermissionDomain getPermissionWithParent(Serializable permissionId)
        throws ApplicationException, SystemException
    {
        Permission permission = permissionRepository.get(Long
                .parseLong(permissionId.toString()));
        PermissionDomain permissionDomain = convertPermissionWithoutSubPermissions(permission);

        if(permission.getParentPermission() != null)
        {
            PermissionDomain parentDomain = convertPermissionWithoutSubPermissions(permission
                    .getParentPermission());

            permissionDomain.setParentPermission(parentDomain);
        }

        return permissionDomain;
    }

    public RoleDomain getRoleWithPermissions(Serializable id)
        throws ApplicationException, SystemException
    {
        return convertRoleWithPermissions(roleRepository.get(id));
    }

    public RoleDomain getRoleWithoutPermissions(Serializable id)
        throws ApplicationException, SystemException
    {
        return convertRoleWithoutPermissions(roleRepository.get(id));
    }

    public RoleDomain getRoleWithoutPermissionsByBCode(String bCode)
        throws ApplicationException, SystemException
    {
        return convertRoleWithoutPermissions(roleRepository.getByBCode(bCode));
    }

    public PagedList<RoleDomain> getRoleList(EntityFilter entityFilter,
            Integer pageNo, Integer pageSize) throws ApplicationException,
        SystemException
    {
        return (PagedList<RoleDomain>) convertRolesWithoutPermissions(roleRepository
                .getRoleList(entityFilter, pageNo, pageSize));
    }

    public boolean canAssignedRole(Serializable roleId)
        throws ApplicationException, SystemException
    {
        List<RoleDomain> roles = getAssignableRolesByUserId(SecurityUtils
                .getUserId(), ProjectContext.getSubsystemCode());
        for (RoleDomain roleDomain : roles)
        {
            if(roleDomain.getId().equals(String.valueOf(roleId)))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 得到用户的子系统权限
     * 
     * @throws SystemException
     * @throws ApplicationException
     * */
    public List<RoleDomain> getRolesByUserWithItemsSelectedViaOp(
            Serializable userId, Serializable masterUserId,
            Serializable subsystemId) throws ApplicationException,
        SystemException
    {
        //得到当前运营商可以赋予角色的权限
        List<RoleDomain> assignableRoles = getAssignableRolesByUserId(
            masterUserId, subsystemId);
        List<RoleDomain> allRoles = this
                .getAllRolesByUserWithItemsSelected(userId);

        //如果是超级管理员的话，返回所有权限
        if(SecurityUtils.hasAdminRole())
        {
            return allRoles;
        }

        //遍历allRoles，去掉不是该子系统的
        for (RoleDomain roleDomain : assignableRoles)
        {
            if(hasRole(allRoles, roleDomain.getId()))
            {
                roleDomain.setSelected("true");
            }
        }
        return assignableRoles;
    }

    private static boolean hasRole(List<RoleDomain> roles, String roleId)
    {
        for (RoleDomain roleDomain : roles)
        {
            if(roleDomain.getId().equals(roleId)
                    && Boolean.parseBoolean(roleDomain.getSelected()) == true)
                return true;
        }
        return false;
    }

    public boolean hasRole(Serializable userId, String permissionCode)
    {
        UserAccount ua = userAccountRepository.getUserAccount(userId);
        List<Role> userRoles = roleRepository.getRolesByUser(ua);
        for (Role role : userRoles)
        {
            if(role.getName().equals(permissionCode))
                return true;
        }
        return false;
    }

    public boolean hasAdminRole(Serializable userId)
    {
        return hasRole(RolesConsts.ROLE_ADMIN);
    }

    public List<RoleDomain> getAssignableRolesByUserId(Serializable userId,
            Serializable subsystemId) throws ApplicationException,
        SystemException
    {
        //如果是admin，返回所有权限
        if(hasAdminRole(userId))
        {
            return convertRolesWithoutPermissions(roleRepository
                    .getAllRoleList());
        }

        List<RoleDomain> roles = new ArrayList<RoleDomain>();
        UserAccount userAccount = userAccountRepository.getUserAccount(userId);
        for (UserRole ur : userAccount.getUserRoles())
        {
            List<RoleDomain> assignableRolesByRoleId = getAssignableRolesByRoleId(
                ur.getRole().getId(), subsystemId);
            if(assignableRolesByRoleId != null)
                roles.addAll(assignableRolesByRoleId);
        }
        return roles;
    }

    public List<RoleDomain> getAssignableRolesByRoleId(Serializable roleId,
            Serializable subsystemId) throws ApplicationException,
        SystemException
    {
        //TODO
        return convertRolesWithoutPermissions(roleRepository
                .getRoleListBySubsystem(roleId, subsystemId));

    }

    public List<RoleDomain> getAllRolesByUserWithItemsSelected(
            Serializable userId) throws ApplicationException, SystemException
    {
        List<Role> allRoles = roleRepository.getAllRoleList();
        UserAccount userAccount = userAccountRepository.getUserAccount(Long
                .parseLong(userId.toString()));
        List<Role> rolesByUser = roleRepository.getRolesByUser(userAccount);

        List<RoleDomain> domainList = new ArrayList<RoleDomain>();

        RoleDomain roleDomain;
        for (Role role : allRoles)
        {
            roleDomain = convertRoleWithoutPermissions(role);
            if(rolesByUser.contains(role))
            {
                roleDomain.setSelected("true");
            }
            domainList.add(roleDomain);
        }

        return domainList;
    }

    public RoleDomain saveRole(RoleDomain roleDomain) throws SystemException,
        ApplicationException
    {
        Role role = new Role();
        convertRoleFromDomain(roleDomain, role);
        return convertRoleWithoutPermissions(role);
    }

    public RoleDomain updateRole(RoleDomain roleDomain) throws SystemException,
        ApplicationException
    {
        Role role = roleRepository.get(Long.parseLong(roleDomain.getId()));
        convertRoleFromDomain(roleDomain, role);
        return convertRoleWithoutPermissions(role);

    }

    public List<PermissionDomain> getRootPermissionsForCurrentUser(
            String platform) throws ApplicationException, SystemException
    {
        return convertPermissionsWithSubPermissions(permissionRepository
                .getPermissionsForRootMenu(platform), null,
            platform, true);
    }

    public List<PermissionDomain> getRootPermissionsForSpecificPlatform(
            String platform) throws ApplicationException, SystemException
    {
        return convertPermissionsWithSubPermissions(permissionRepository
                .getRootPermissionsForPlatform(platform), null, platform, false);
    }

    public List<PermissionDomain> getRootPermissionsForSpecificPlatformAndRole(
            String platform, Serializable roleId) throws ApplicationException,
        SystemException
    {
        List<Permission> allPermissions = permissionRepository
                .getRootPermissionsForPlatform(platform);
        List<Permission> selectedPermissions = permissionRepository
                .getPermissionsByPlatformAndRole(platform, roleId);

        List<PermissionDomain> domainList = new ArrayList<PermissionDomain>();

        PermissionDomain permissionDomain;
        for (Permission permission : allPermissions)
        {
            permissionDomain = convertPermissionWithSubPermissionsForRole(
                permission, platform, roleId, selectedPermissions);

            if(selectedPermissions.contains(permission))
            {
                permissionDomain.setSelected("true");
            }

            domainList.add(permissionDomain);
        }

        return domainList;
    }

    private PermissionDomain convertPermissionWithSubPermissionsForRole(
            Permission permission, String platform, Serializable roleId,
            Collection<Permission> selectedList) throws ApplicationException,
        SystemException
    {
        PermissionDomain permissionDomain = convertPermissionWithoutSubPermissions(permission);

        List<PermissionDomain> subDomains = new ArrayList<PermissionDomain>();

        Collection<Permission> subPermissions = permission
                .getChildPermissions();

        PermissionDomain subDomain;
        if(subPermissions != null && subPermissions.size() > 0)
        {
            for (Permission subPermission : subPermissions)
            {
                subDomain = convertPermissionWithSubPermissionsForRole(
                    subPermission, platform, roleId, selectedList);
                if(selectedList.contains(subPermission))
                {
                    subDomain.setSelected("true");
                }

                subDomains.add(subDomain);
            }

        }

        permissionDomain.setChildPermissions(subDomains);

        return permissionDomain;
    }

    private List<PermissionDomain> convertPermissionsWithSubPermissions(
            Collection<Permission> permissionList,
            PermissionDomain parentPermission, String platform,
            boolean forMenuOnly) throws ApplicationException, SystemException
    {
        List<PermissionDomain> menuList = new ArrayList<PermissionDomain>();
        PermissionDomain permissionDomain;
        List<Permission> subMenus;
        if(permissionList != null && permissionList.size() > 0)
        {
            for (Permission permission : permissionList)
            {
                permissionDomain = this
                        .convertPermissionWithoutSubPermissions(permission);

                if(forMenuOnly)
                {
                    subMenus = permissionRepository.getPermissionsForSubMenu(
                        permission.getId(), platform);

                }
                else
                {
                    subMenus = new ArrayList<Permission>();
                    subMenus.addAll(permission.getChildPermissions());

                }

                convertPermissionsWithSubPermissions(subMenus,
                    permissionDomain, platform, forMenuOnly);
                menuList.add(permissionDomain);
            }

        }
        if(parentPermission != null)
        {
            parentPermission.setChildPermissions(menuList);
        }
        return menuList;
    }

    private PermissionDomain convertPermissionWithoutSubPermissions(
            Permission permission) throws ApplicationException, SystemException
    {
        PermissionDomain permissionDomain = new PermissionDomain();

        ConvertUtil.entity2domain(permission, permissionDomain);

        return permissionDomain;

    }

    @SuppressWarnings("unchecked")
    private List<PermissionDomain> convertPermissionsWithoutSubPermissions(
            List<Permission> permissionList) throws ApplicationException,
        SystemException
    {
        List<PermissionDomain> domainList = new ConvertIterator().iterate(
            permissionList, new ConvertPermissionWithoutSubCallback());

        if(permissionList instanceof PagedList)
        {
            ListUtil.clonePagedInfo((PagedList<PermissionDomain>) domainList,
                (PagedList<Permission>) permissionList);
        }
        return domainList;
    }

    private RoleDomain convertRoleWithoutPermissions(Role role)
        throws ApplicationException, SystemException
    {
        if(role == null)
        {
            return null;
        }
        RoleDomain roleDomain = new RoleDomain();

        ConvertUtil.entity2domain(role, roleDomain);

        return roleDomain;
    }

    private RoleDomain convertRoleWithPermissions(Role role)
        throws ApplicationException, SystemException
    {
        if(role == null)
        {
            return null;
        }
        RoleDomain roleDomain = convertRoleWithoutPermissions(role);

        Collection<RolePermission> rolePermissions = role.getRolePermissions();

        PermissionDomain permissionDomain;
        List<PermissionDomain> permissionDomains = new ArrayList<PermissionDomain>();
        for (RolePermission rolePermission : rolePermissions)
        {
            permissionDomain = new PermissionDomain();

            ConvertUtil.entity2domain(rolePermission.getPermission(),
                permissionDomain);

            permissionDomains.add(permissionDomain);
        }

        roleDomain.setSelectedPermissions(permissionDomains);

        return roleDomain;
    }

    private List<RoleDomain> convertRolesWithoutPermissions(List<Role> roles)
        throws ApplicationException, SystemException
    {
        if(roles == null || roles.size() == 0)
        {
            return null;
        }
        List<RoleDomain> domainList;

        if(roles instanceof PagedList)
        {
            domainList = new PagedList<RoleDomain>();

            ListUtil.clonePagedInfo((PagedList<RoleDomain>) domainList,
                (PagedList<Role>) roles);
        }
        else
        {
            domainList = new ArrayList<RoleDomain>();
        }

        RoleDomain roleDomain;
        for (Role role : roles)
        {
            roleDomain = convertRoleWithoutPermissions(role);
            domainList.add(roleDomain);
        }

        return domainList;
    }

    private void convertRoleFromDomain(RoleDomain roleDomain, Role role)
        throws SystemException, ApplicationException
    {
        assert role != null;

        boolean hasError = ConvertUtil.domain2entity(roleDomain, role);

        if(hasError)
        {
            throw new ApplicationException();
        }

        roleRepository.save(role);

    }

    public List<PermissionDomain> getPermissionByCurrentUser()
        throws ApplicationException, SystemException
    {
        return convertPermissionsWithoutSubPermissions(permissionRepository
                .getPermissionListByUser(Long.parseLong(getUserId().toString())));
    }

    public List<PermissionDomain> getPermissionByRole(Serializable roleId)
        throws ApplicationException, SystemException
    {
        return convertPermissionsWithoutSubPermissions(permissionRepository
                .getPermissionListByRole(Long.parseLong(roleId.toString())));
    }

    public List<PermissionDomain> getAllPermissions()
        throws ApplicationException, SystemException
    {
        return convertPermissionsWithoutSubPermissions(permissionRepository
                .getAllPermissions());
    }

    public void updatePermissionList(PermissionDomain... domains)
        throws SystemException, ApplicationException
    {
        Permission permission;

        boolean hasError = false;

        if(domains != null && domains.length > 0)
        {
            for (PermissionDomain domain : domains)
            {
                permission = permissionRepository.get(Long.parseLong(domain
                        .getId()));

                if(ConvertUtil.domain2entity(domain, permission))
                {
                    hasError = true;
                }
            }
        }

        if(hasError)
        {
            throw new ApplicationException();
        }
    }

    private class ConvertIterator<Entity, Domain extends BaseDomain>
    {
        public List<Domain> iterate(Collection<Entity> collection,
                ConvertCallback<Entity, Domain> callback)
            throws ApplicationException, SystemException
        {
            List<Domain> domainList = new PagedList<Domain>();
            Converter<Entity, Domain> converter = new Converter<Entity, Domain>();
            for (Entity entity : collection)
            {
                domainList.add(converter.convert(entity, callback));
            }

            return domainList;
        }

    }

    private class Converter<Entity, Domain extends BaseDomain>
    {
        public Domain convert(Entity entity,
                ConvertCallback<Entity, Domain> callback)
            throws ApplicationException, SystemException
        {
            return callback.convert(entity);
        }
    }

    private interface ConvertCallback<Entity, Domain extends BaseDomain>
    {
        public Domain convert(Entity entity) throws ApplicationException,
            SystemException;
    }

    @SuppressWarnings("unused")
    private class ConvertPermissionWithoutSubCallback implements
            ConvertCallback<Permission, PermissionDomain>
    {

        public PermissionDomain convert(Permission entity)
            throws ApplicationException, SystemException
        {
            PermissionDomain domain = new PermissionDomain();

            ConvertUtil.entity2domain(entity, domain);
            return domain;
        }

    }

    public List<SubsystemDomain> getAllSubsystem() throws ApplicationException,
        SystemException
    {

        List<SubsystemDomain> list = new ArrayList<SubsystemDomain>();
        for (Subsystem subsystem : subsystemRepository.getAllSubsystems())
        {
            SubsystemDomain subsystemDomain = new SubsystemDomain();
            ConvertUtil.entity2domain(subsystem, subsystemDomain);
            list.add(subsystemDomain);
        }
        return list;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param roleRepository
     *            The roleRepository to set.
     */
    @Required
    public void setRoleRepository(IRoleRepository roleRepository)
    {
        this.roleRepository = roleRepository;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param permissionRepository
     *            The permissionRepository to set.
     */
    @Required
    public void setPermissionRepository(
            IPermissionRepository permissionRepository)
    {
        this.permissionRepository = permissionRepository;
    }

    @Required
    public void setUserAccountRepository(
            IUserAccountRepository userAccountRepository)
    {
        this.userAccountRepository = userAccountRepository;
    }

    @Required
    public void setSubsystemRepository(ISubsystemRepository subsystemRepository)
    {
        this.subsystemRepository = subsystemRepository;
    }

}

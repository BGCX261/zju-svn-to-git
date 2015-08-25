package com.jsict.platform.validator.bean;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.jsict.base.BaseValidator;
import com.jsict.base.context.ProjectContext;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.MessageInfo;
import com.jsict.platform.domain.RoleDomain;
import com.jsict.platform.entity.Role;
import com.jsict.platform.entity.UserAccount;
import com.jsict.platform.repository.IRoleRepository;
import com.jsict.platform.repository.IUserAccountRepository;
import com.jsict.platform.service.IAuthorizationService;
import com.jsict.platform.util.SecurityUtils;
import com.jsict.platform.validator.IAuthorizationValidator;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
@Transactional
public class AuthorizationValidator extends
        BaseValidator<IAuthorizationService> implements IAuthorizationValidator
{
    private IRoleRepository roleRepository;

    private IUserAccountRepository userAccountRepository;

    @Required
    public void setUserAccountRepository(
            IUserAccountRepository userAccountRepository)
    {
        this.userAccountRepository = userAccountRepository;
    }

    @Required
    public void setRoleRepository(IRoleRepository roleRepository)
    {
        this.roleRepository = roleRepository;
    }

    public void deleteRole(RoleDomain roleDomain) throws ApplicationException
    {
        //不能删除管理员组
        Role role = roleRepository.get(Long.parseLong(roleDomain.getId()));
        if("管理员".equals(role.getName()))
        {

            ProjectContext.getErrorList().add(new MessageInfo("P004"));
            throw new ApplicationException();
        }
    }

    public void saveRole(RoleDomain roleDomain) throws ApplicationException
    {
        checkRoleDomainName(roleDomain);
    }

    public void updateRole(RoleDomain roleDomain) throws ApplicationException
    {
        //checkRoleDomainName(roleDomain);
    }

    private void checkRoleDomainName(RoleDomain roleDomain)
        throws ApplicationException
    {
        //role name不能重复
        Role role = roleRepository.getByName(roleDomain.getName());
        if(role != null
                && (StringUtils.isBlank(roleDomain.getId()) || (StringUtils
                        .isBlank(roleDomain.getId()) == false && Long
                        .parseLong(roleDomain.getId()) == role.getId())))
        {
            ProjectContext.getErrorList().add(
                new MessageInfo("P005", new String[]{role.getName()}));
            throw new ApplicationException();
        }
    }

    public void updateParticialUserRoles(Serializable userId,
            Serializable masterUserId, Serializable subsystemId,
            Serializable[] roleIds) throws ApplicationException,
        SystemException
    {
        UserAccount userAccount = userAccountRepository.getUserAccount(userId);
        if(SecurityUtils.hasAdminRole())
            return;
        //检查是否有权限，指定给用户
        for (Serializable roleId : roleIds)
        {
            Role role = roleRepository.get(roleId);
            if(roleRepository.canAssignRoleByUserId(masterUserId, roleId) == false)
            {
                ProjectContext.getErrorList().add(
                    new MessageInfo("P006", new String[]{userAccount.getName(),
                            role.getName()}));
                throw new ApplicationException();
            }
        }

    }
}

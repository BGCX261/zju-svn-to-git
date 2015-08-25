/**
 * IUserAccountRepository.java 2008-11-10 下午03:06:57 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.repository.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.BaseRepository;
import com.jsict.base.context.ProjectContext;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.util.Op;
import com.jsict.platform.constants.CodeKey;
import com.jsict.platform.dao.IUserAccountDao;
import com.jsict.platform.dao.IUserRoleDao;
import com.jsict.platform.entity.UserAccount;
import com.jsict.platform.repository.IUserAccountRepository;
import com.jsict.platform.util.SecurityUtils;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class UserAccountRepository extends BaseRepository implements
        IUserAccountRepository
{
    private IUserAccountDao userAccountDao;

    private static final int MAX_WRONG_COUNT = 5;

    public IUserRoleDao userRoleDao;

    private void ownUserEntityFilter(EntityFilter ef, Serializable creatorId,
            Serializable subsystemCode)
    {

        if(creatorId != null && !Long.valueOf(0L).equals(creatorId))
        {

            ef.addFilter("creator.company.id", Long.parseLong(String
                    .valueOf(creatorId)));
        }
        if(subsystemCode != null
                && !StringUtils.isBlank(String.valueOf(subsystemCode)))
        {
            ef.addFilter("subsystem.id", String.valueOf(subsystemCode));
        }
    }

    private Serializable getCreatorCompanyId()
    {
        if(SecurityUtils.hasAdminRole() == false)
        {
            return SecurityUtils.getUser().getCompanyId();
        }
        return null;

    }

    private Serializable getSubsystemId()
    {
        if(SecurityUtils.hasAdminRole() == false)
        {
            return ProjectContext.getSubsystemCode();
        }
        return null;
    }

    public void delete(UserAccount userAccount)
    {
        userAccountDao.delete(userAccount);
    }

    public UserAccount getUserAccount(Serializable userAccountId)
    {
        return userAccountDao
                .get(Long.parseLong(String.valueOf(userAccountId)));
    }

    public UserAccount getUserAccountByEmail(String email)
    {
        return userAccountDao.getUserAccountByEmail(StringUtils.trim(email));
    }

    public PagedList<UserAccount> getUserAccountList(EntityFilter entityFilter,
            Integer pageNo, Integer pageSize)
    {
        return userAccountDao.getPagedList(entityFilter, pageNo, pageSize);
    }

    public List<UserAccount> getUserAccountListByCompany(Serializable companyId)
    {

        return userAccountDao
                .getUserAccountListByCompany(companyId, null, null);
    }

    public List<UserAccount> getUserAccountListByRole(Serializable roleId)
    {
        return userAccountDao.getUserAccountListByRole(roleId, null, null);
    }

    public List<UserAccount> getUserAccountListByRole(Serializable roleId,
            Serializable companyId, String userName)
    {
        return userAccountDao.getUserAccountListByRole(roleId, companyId,
            userName, null, null);
    }

    public PagedList<UserAccount> getOwnUserAccountList(
            EntityFilter entityFilter, Integer pageNo, Integer pageSize)
    {
        if(SecurityUtils.hasAdminRole() == false)
        {
            entityFilter.addFilter("creator.company.id", getCreatorCompanyId());
            entityFilter.addFilter("subsystem.id", getSubsystemId());
            entityFilter.addFilter("status", Op.NOT_EQUAL,
                CodeKey.USER_STATUS_UNAVAILABLE);
        }
        return userAccountDao.getPagedList(entityFilter, pageNo, pageSize);
    }

    public List<UserAccount> getOwnUserAccountListByCompany(
            Serializable companyId)
    {

        return userAccountDao.getUserAccountListByCompany(companyId,
            getCreatorCompanyId(), getSubsystemId());
    }

    public List<UserAccount> getOwnUserAccountListByRole(Serializable roleId)
    {
        return userAccountDao.getUserAccountListByRole(roleId,
            getCreatorCompanyId(), getSubsystemId());
    }

    public List<UserAccount> getOwnUserAccountListByRole(Serializable roleId,
            Serializable companyId, String userName)
    {
        return userAccountDao.getUserAccountListByRole(roleId, companyId,
            userName, getCreatorCompanyId(), getSubsystemId());
    }

    public UserAccount save(UserAccount userAccount)
    {
        return userAccountDao.save(userAccount);
    }

    public void disable(UserAccount userAccount)
    {
        userAccount.setStatus(CodeKey.USER_STATUS_UNAVAILABLE);
        userAccountDao.save(userAccount);
    }

    public void enable(UserAccount userAccount)
    {
        userAccount.setStatus(CodeKey.USER_STATUS_AVAILABLE);
        userAccountDao.save(userAccount);
    }

    public void freeze(UserAccount userAccount)
    {
        userAccount.setStatus(CodeKey.USER_STATUS_FROZEN);
        userAccountDao.save(userAccount);
    }

    public UserAccount getUserAccountByLoginId(String loginId)
    {
        return userAccountDao.getUserAccountByLoginId(loginId);
    }

    public void detachRolesFromUser(UserAccount userAccount)
    {
        assert userAccount != null;

        userRoleDao.deleteForUser(userAccount.getId());
    }

    public void detachRoleFromUser(Serializable userId, Serializable roleId)
    {
        assert userId != null;

        userRoleDao.deleteForUser(userId, roleId);
    }

    public void recordLoginInfo(Serializable userAccountId, String loginIp,
            Date loginTime, boolean successful)
    {
        UserAccount userAccount = getUserAccount(userAccountId);
        if(successful)
        {
            userAccount.setLoginIp(loginIp);
            userAccount.setLoginTime(loginTime);
            //automatically clear wrong count
            userAccount.setWrongCount(0);
        }
        else
        {
            userAccount.setWrongCount(userAccount.getWrongCount() + 1);
            if(userAccount.getWrongCount() == MAX_WRONG_COUNT)
            {
                userAccount.setStatus(CodeKey.USER_STATUS_FROZEN);
            }
        }
        userAccountDao.save(userAccount);
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param userAccountDao
     *            The userAccountDao to set.
     */
    @Required
    public void setUserAccountDao(IUserAccountDao userAccountDao)
    {
        this.userAccountDao = userAccountDao;
    }

    @Required
    public void setUserRoleDao(IUserRoleDao userRoleDao)
    {
        this.userRoleDao = userRoleDao;
    }

}

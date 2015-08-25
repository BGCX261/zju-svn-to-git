/**
 * IUserService.java 2008-11-10 下午03:39:57 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jsict.base.IBaseService;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.platform.domain.UserAccountDomain;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * .
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public interface IUserService extends IBaseService
{

    /**
     * Gets the user account list by company.
     * 
     * @param companyId
     *            the company id
     * 
     * @return the user account list by company
     * @throws SystemException
     * @throws ApplicationException
     */
    List<UserAccountDomain> getUserAccountListByCompany(Serializable companyId)
        throws ApplicationException, SystemException;

    /**
     * Gets the user account list by role.
     * 
     * @param roleId
     *            the role id
     * 
     * @return the user account list by role
     * @throws SystemException
     * @throws ApplicationException
     */
    List<UserAccountDomain> getUserAccountListByRole(Serializable roleId)
        throws ApplicationException, SystemException;

    /**
     * Gets the user account.
     * 
     * @param userAccountId
     *            the user account id
     * 
     * @return the user account
     * 
     * @throws ApplicationException
     *             the application exception
     * @throws SystemException
     *             the system exception
     */
    UserAccountDomain getUserAccountWithoutRoles(Serializable userAccountId)
        throws ApplicationException, SystemException;

    /**
     * Gets the user account list.
     * 
     * @param entityFilter
     *            the entity filter
     * @param pageNo
     *            the page no
     * @param pageSize
     *            the page size
     * 
     * @return the user account list
     * 
     * @throws ApplicationException
     *             the application exception
     * @throws SystemException
     *             the system exception
     */
    PagedList<UserAccountDomain> getUserAccountList(EntityFilter entityFilter,
            Integer pageNo, Integer pageSize) throws ApplicationException,
        SystemException;

    /**
     * <p>
     * Description: The User account domain.
     * </p>
     * 
     * @param userAccountDomain
     *            the user account domain
     * 
     * @return the user account domain
     * 
     * @throws SystemException
     *             the system exception
     * @throws ApplicationException
     *             the application exception
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    UserAccountDomain save(UserAccountDomain userAccountDomain)
        throws SystemException, ApplicationException;

    /**
     * <p>
     * Description: The Delete.
     * </p>
     * 
     * @param userAccountDomain
     *            the user account domain
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    void deleteUser(UserAccountDomain userAccountDomain);

    /**
     * <p>
     * Description: The User account domain.
     * </p>
     * 
     * @param UserAccountDomain
     *            the user account domain
     * 
     * @return the user account domain
     * 
     * @throws SystemException
     *             the system exception
     * @throws ApplicationException
     *             the application exception
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    UserAccountDomain update(UserAccountDomain UserAccountDomain,
            Serializable... roleIds) throws SystemException,
        ApplicationException;

    /**
     * <p>
     * Description: The Disable user.
     * </p>
     * 
     * @param userId
     *            the user id
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    void disableUser(Serializable userId);

    /**
     * <p>
     * Description: The Enable user.
     * </p>
     * 
     * @param userId
     *            the user id
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    void enableUser(Serializable userId);

    public void resetPassword(Serializable userId);

    public PagedList<UserAccountDomain> getUserAccountListWithRoles(
            EntityFilter entityFilter, Integer pageNo, Integer pageSize)
        throws ApplicationException, SystemException;

    void freezeUser(Serializable userId) throws ApplicationException;

    void recordLoginInfo(Serializable userId, String loginIp, Date loginTime,
            boolean success);

    void resetPassword(Serializable userId, String oldpassword,
            String newpassword);

    UserAccountDomain getUserAccountWithRoles(Serializable userAccountId)
        throws ApplicationException, SystemException;

    List<UserAccountDomain> getUserAccountListByRole(Serializable roleId,
            Serializable companyId, String userName)
        throws ApplicationException, SystemException;

    List<UserAccountDomain> getOwnUserAccountListByRole(Serializable roleId,
            Serializable companyId, String userName)
        throws ApplicationException, SystemException;

    PagedList<UserAccountDomain> getOwnUserAccountList(
            EntityFilter entityFilter, Integer pageNo, Integer pageSize)
        throws ApplicationException, SystemException;

    PagedList<UserAccountDomain> getOwnUserAccountListWithRoles(
            EntityFilter entityFilter, Integer pageNo, Integer pageSize)
        throws ApplicationException, SystemException;

    List<UserAccountDomain> getOwnUserAccountListByCompany(
            Serializable companyId) throws ApplicationException,
        SystemException;

    List<UserAccountDomain> getOwnUserAccountListByRole(Serializable roleId)
        throws ApplicationException, SystemException;

}

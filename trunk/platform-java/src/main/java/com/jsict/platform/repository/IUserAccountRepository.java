/**
 * IUserAccountRepository.java 2008-11-10 下午03:06:57 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jsict.base.IBaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
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
public interface IUserAccountRepository extends IBaseRepository
{

    /**
     * <p>
     * Description: The Delete.
     * </p>
     * 
     * @param userAccount
     *            the user account
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    public void delete(UserAccount userAccount);

    /**
     * Gets the user account.
     * 
     * @param userAccountId
     *            the user account id
     * 
     * @return the user account
     */
    public UserAccount getUserAccount(Serializable userAccountId);

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
     */
    public PagedList<UserAccount> getUserAccountList(EntityFilter entityFilter,
            Integer pageNo, Integer pageSize);

    /**
     * Gets the user account list by company.
     * 
     * @param companyId
     *            the company id
     * 
     * @return the user account list by company
     */
    public List<UserAccount> getUserAccountListByCompany(Serializable companyId);

    /**
     * Gets the user account list by role.
     * 
     * @param roleId
     *            the role id
     * 
     * @return the user account list by role
     */
    public List<UserAccount> getUserAccountListByRole(Serializable roleId);

    /**
     * <p>
     * Description: The User account.
     * </p>
     * 
     * @param userAccount
     *            the user account
     * 
     * @return the user account
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    public UserAccount save(UserAccount userAccount);

    /**
     * <p>
     * Description: The Disable.
     * </p>
     * 
     * @param userAccount
     *            the user account
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    public void disable(UserAccount userAccount);

    /**
     * <p>
     * Description: The Enable.
     * </p>
     * 
     * @param userAccount
     *            the user account
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    public void enable(UserAccount userAccount);

    /**
     * <p>
     * Description: The Freeze.
     * </p>
     * 
     * @param userAccount
     *            the user account
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    public void freeze(UserAccount userAccount);

    /**
     * Gets the user account by login id.
     * 
     * @param loginId
     *            the login id
     * 
     * @return the user account by login id
     */
    UserAccount getUserAccountByLoginId(String loginId);

    public void detachRolesFromUser(UserAccount userAccount);

    UserAccount getUserAccountByEmail(String email);

    void recordLoginInfo(Serializable userAccountId, String loginIp,
            Date loginTime, boolean successful);

    List<UserAccount> getUserAccountListByRole(Serializable roleId,
            Serializable companyId, String userName);

    PagedList<UserAccount> getOwnUserAccountList(EntityFilter entityFilter,
            Integer pageNo, Integer pageSize);

    List<UserAccount> getOwnUserAccountListByCompany(Serializable companyId);

    List<UserAccount> getOwnUserAccountListByRole(Serializable roleId);

    List<UserAccount> getOwnUserAccountListByRole(Serializable roleId,
            Serializable companyId, String userName);

    void detachRoleFromUser(Serializable userId, Serializable roleId);

}

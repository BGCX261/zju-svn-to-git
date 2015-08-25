/**
 * IUserAccountDao.java 2008-11-10 下午03:06:57 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dao;

import java.io.Serializable;
import java.util.List;

import com.jsict.base.dao.IBaseDao;
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
public interface IUserAccountDao extends IBaseDao<UserAccount>
{

    /**
     * Gets the user account list by company.
     * 
     * @param companyId
     *            the company id
     * 
     * @return the user account list by company
     */
    List<UserAccount> getUserAccountListByCompany(Serializable companyId,
            Serializable creatorId, Serializable subsystemId);

    /**
     * Gets the user account list by role.
     * 
     * @param roleId
     *            the role id
     * 
     * @return the user account list by role
     */
    public List<UserAccount> getUserAccountListByRole(Serializable roleId,
            Serializable creatorId, Serializable subsystemId);

    /**
     * Gets the user account by login id.
     * 
     * @param loginId
     *            the login id
     * 
     * @return the user account by login id
     */
    public UserAccount getUserAccountByLoginId(String loginId);

    UserAccount getUserAccountByEmail(String email);

    List<UserAccount> getUserAccountListByRole(Serializable roleId,
            Serializable companyId, String userName, Serializable creatorId,
            Serializable subsystemId);
}

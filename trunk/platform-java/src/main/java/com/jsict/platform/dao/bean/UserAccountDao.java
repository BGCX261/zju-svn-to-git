/**
 * UserAccountDao.java 2008-11-10 下午03:17:03 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dao.bean;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jsict.base.dao.BaseDao;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.util.Op;
import com.jsict.platform.constants.CodeKey;
import com.jsict.platform.dao.IUserAccountDao;
import com.jsict.platform.entity.UserAccount;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class UserAccountDao extends BaseDao<UserAccount> implements
        IUserAccountDao
{
    //public final static String GET_USER_ACCOUNT_LIST_BY_COMPANY = "from UserAccount where company.id = ?";
    public final static String GET_USER_ACCOUNT_LIST_BY_COMPANY = "from UserAccount";

    //public final static String GET_USER_ACCOUNT_LIST_BY_ROLE = "select ua from UserAccount ua join ua.userRoles ur where ur.role.id = ?";
    public final static String GET_USER_ACCOUNT_LIST_BY_ROLE = "select ua from UserAccount ua join ua.userRoles ur";

    public final static String GET_USER_ACCOUNT_LIST_BY_EMPTYROLE = "select ua from UserAccount ua where ua.userRoles.size=0 ";

    public final static String GET_USER_ACCOUNT_BY_LOGIN_ID = "from UserAccount where loginId = ?";

    public final static String GET_USER_ACCOUNT_BY_EMAIL = "from UserAccount where email=?";

    public List<UserAccount> getUserAccountListByCompany(
            Serializable companyId, Serializable creatorCompanyId,
            Serializable subsystemId)
    {
        EntityFilter ef = new EntityFilter();
        ef.addFilter("company.id", companyId);
        ef.addFilter("creator.company.id", creatorCompanyId == null ? null
                : Long.parseLong(String.valueOf(creatorCompanyId)));
        ef.addFilter("subsystem.id", subsystemId == null ? null : String
                .valueOf(subsystemId));
        ef.addFilter("status", Op.NOT_EQUAL, CodeKey.USER_STATUS_UNAVAILABLE);

        return executeQuery(GET_USER_ACCOUNT_LIST_BY_COMPANY, companyId);
    }

    public List<UserAccount> getUserAccountListByRole(Serializable roleId,
            Serializable creatorCompanyId, Serializable subsystemId)
    {
        EntityFilter ef = new EntityFilter();
        ef.addFilter("ua.creator.company.id", creatorCompanyId == null ? null
                : Long.parseLong(String.valueOf(creatorCompanyId)));
        ef.addFilter("ua.subsystem.id", subsystemId == null ? null : String
                .valueOf(subsystemId));
        ef.addFilter("status", Op.NOT_EQUAL, CodeKey.USER_STATUS_UNAVAILABLE);

        if(Long.parseLong(String.valueOf(roleId)) == -1L)
        {
            return executeQuery(GET_USER_ACCOUNT_LIST_BY_EMPTYROLE);
        }
        else
        {
            ef.addFilter("ur.role.id", roleId);

            return executeQuery(GET_USER_ACCOUNT_LIST_BY_ROLE, roleId);
        }
    }

    public List<UserAccount> getUserAccountListByRole(Serializable roleId,
            Serializable companyId, String userName,
            Serializable creatorCompanyId, Serializable subsystemId)
    {
        String sql;
        EntityFilter ef = new EntityFilter();

        ef.addFilter("ua.creator.company.id", creatorCompanyId == null ? null
                : Long.parseLong(String.valueOf(creatorCompanyId)));
        ef.addFilter("ua.subsystem.id", subsystemId == null ? null : String
                .valueOf(subsystemId));
        ef
                .addFilter("ua.status", Op.NOT_EQUAL,
                    CodeKey.USER_STATUS_UNAVAILABLE);

        if(Long.parseLong(String.valueOf(roleId)) == -1L)
        {
            sql = GET_USER_ACCOUNT_LIST_BY_EMPTYROLE;
            if(companyId != null
                    && StringUtils.isBlank(String.valueOf(companyId)) == false)
            {
                sql += " and ua.company.id='" + companyId + "'";
            }

            if(userName != null
                    && StringUtils.isBlank(String.valueOf(userName)) == false)
            {
                sql += " and ua.name like '" + userName + "%'";
            }
            return executeQuery(sql, ef);
        }
        else
        {
            sql = GET_USER_ACCOUNT_LIST_BY_ROLE;
            ef.addFilter("ur.role.id", roleId);
            if(companyId != null
                    && StringUtils.isBlank(String.valueOf(companyId)) == false)
            {
                ef.addFilter("ua.company.id", Long.parseLong(String
                        .valueOf(companyId)));
            }

            if(userName != null
                    && StringUtils.isBlank(String.valueOf(userName)) == false)
            {
                ef.addFilter("ua.name", Op.LIKE, userName + "*");
            }
            return executeQuery(sql, ef);
        }

    }

    public UserAccount getUserAccountByLoginId(String loginId)
    {
        return getFirst(executeQuery(GET_USER_ACCOUNT_BY_LOGIN_ID, loginId));
    }

    public UserAccount getUserAccountByEmail(String email)
    {
        return getFirst(executeQuery(GET_USER_ACCOUNT_BY_EMAIL, email));
    }

}

/**
 * UserDwrBean.java 2008-11-17 上午10:39:28 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dwr;

import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.platform.domain.UserAccountDomain;
import com.jsict.platform.service.IUserService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class UserDwrBean
{
    IUserService userService;

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param userService
     *            The userService to set.
     */
    @Required
    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }

    /**
     * @param userId
     * @see com.jsict.platform.service.IUserService#resetPassword(java.io.Serializable)
     */
    public boolean resetPasswordBySendMail(String userId)
    {
        try
        {
            userService.resetPassword(userId);
            return true;
        }
        catch (Throwable e)
        {

            return false;
        }
    }

    public UserAccountDomain getUserAccountWithoutRoles(Long userAccountId)
        throws ApplicationException, SystemException
    {
        return userService.getUserAccountWithoutRoles(userAccountId);
    }

    public boolean resetPassword(String oldpassword, String newpassword)
    {
        try
        {
            userService.resetPassword(userService.getUserId(), oldpassword,
                newpassword);
            return true;
        }
        catch (Throwable e)
        {
            return false;
        }
    }

}

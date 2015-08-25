/**
 * PermissionDwrBean.java 2008-11-17 上午10:39:28 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dwr;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.platform.domain.PermissionDomain;
import com.jsict.platform.service.IAuthorizationService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class PermissionDwrBean
{
    IAuthorizationService authorizationService;

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param authorizationService
     *            The authorizationService to set.
     */
    @Required
    public void setAuthorizationService(
            IAuthorizationService authorizationService)
    {
        this.authorizationService = authorizationService;
    }

    /**
     * @param domains
     * @throws SystemException
     * @throws ApplicationException
     * @see com.jsict.platform.service.IAuthorizationService#updatePermissionList(com.jsict.platform.domain.PermissionDomain[])
     */
    public boolean updatePermission(PermissionDomain domain)
        throws SystemException, ApplicationException
    {
        try
        {
            authorizationService.updatePermissionList(domain);
            System.out.println(domain.getDescription());
            return true;
        }
        catch (RuntimeException e)
        {
            
            return false;
        }
    }

    /**
     * @param roleId
     * @param permissionIds
     * @see com.jsict.platform.service.IAuthorizationService#updateRolePermissions(java.io.Serializable,
     *      java.io.Serializable[])
     */
    public boolean updateRolePermissions(String platform, String roleId,
            String[] permissionIds)
    {
        try
        {
            authorizationService.updateRolePermissions(platform, roleId,
                (Serializable[]) permissionIds);
            return true;
        }
        catch (RuntimeException e)
        {
            
            return false;
        }
    }

}

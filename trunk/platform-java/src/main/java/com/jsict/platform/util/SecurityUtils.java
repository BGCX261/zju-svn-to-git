/**
 * SecurityUtils.java 2008-12-14 下午10:53:24 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.util;

import java.io.Serializable;

import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

import com.jsict.base.security.User;
import com.jsict.platform.constants.RolesConsts;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class SecurityUtils
{
    public static Serializable getUserId()
    {
        User user = getUser();

        return user == null ? null : user.getUserId();
    }

    public static User getUser()
    {
        SecurityContext context = SecurityContextHolder.getContext();
        return (User) context.getAuthentication().getPrincipal();

    }

    public static boolean hasPermission(String permissionCode)
    {
        return getUser().hasPermission(permissionCode);
    }

    public static boolean hasRole(String roleCode)
    {
        return getUser().hasRole(roleCode);
    }

    public static boolean hasPermissionByVarName(String permissionCode)
    {
        return getUser().hasPermission(
            PermissionsKeyHelper.getPermissionCode(permissionCode));
    }

    public static boolean hasRoleByVarName(String roleCode)
    {
        return getUser().hasRole(PermissionsKeyHelper.getRoleCode(roleCode));
    }

    public static boolean hasAdminRole()
    {
        return getUser().hasRole(RolesConsts.ROLE_ADMIN);
    }

    public static boolean hasPlatformPermission(String platformId)
    {
        return getUser().hasPlatformPermission(platformId);

    }

}

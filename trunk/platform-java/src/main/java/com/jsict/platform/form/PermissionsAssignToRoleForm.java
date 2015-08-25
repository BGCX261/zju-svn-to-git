/**
 * PermissionsAssignToRoleForm.java 2008-11-16 下午01:07:56 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.form;

import java.util.List;

import com.jsict.base.form.BaseForm;
import com.jsict.platform.domain.PermissionDomain;
import com.jsict.platform.domain.PlatformDomain;
import com.jsict.platform.domain.RoleDomain;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class PermissionsAssignToRoleForm extends BaseForm
{

    private List<PermissionDomain> permission;

    private String permissionsString;

    private List<PlatformDomain> platforms;

    private String currentPlatformId;

    private RoleDomain roleDomain;

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String currentPlatform.
     */
    public String getCurrentPlatformId()
    {
        return currentPlatformId;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param currentPlatform
     *            The currentPlatform to set.
     */
    public void setCurrentPlatformId(String currentPlatform)
    {
        this.currentPlatformId = currentPlatform;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return List<String> platforms.
     */
    public List<PlatformDomain> getPlatforms()
    {
        return platforms;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param platforms
     *            The platforms to set.
     */
    public void setPlatforms(List<PlatformDomain> platforms)
    {
        this.platforms = platforms;
    }

    public void setPermission(
            List<PermissionDomain> rootPermissionsForSpecificPlatform)
    {

        this.permission = rootPermissionsForSpecificPlatform;

    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return List<PermissionDomain> permission.
     */
    public List<PermissionDomain> getPermission()
    {
        return permission;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String permissionString.
     */
    public String getPermissionsString()
    {
        return permissionsString;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param permissionString
     *            The permissionString to set.
     */
    public void setPermissionsString(String permissionsString)
    {
        this.permissionsString = permissionsString;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param roleDomain
     *            The roleDomain to set.
     */
    public void setRoleDomain(RoleDomain roleDomain)
    {
        this.roleDomain = roleDomain;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return RoleDomain roleDomain.
     */
    public RoleDomain getRoleDomain()
    {
        return roleDomain;
    }

}

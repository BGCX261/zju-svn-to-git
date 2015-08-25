/**
 * PermissionsAssignToRoleAction.java 2008-11-14 下午05:30:17 lgq 版权所有 (c)
 * 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.action;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.action.BaseAction;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.platform.domain.PermissionDomain;
import com.jsict.platform.domain.PlatformDomain;
import com.jsict.platform.domain.RoleDomain;
import com.jsict.platform.form.PermissionsAssignToRoleForm;
import com.jsict.platform.service.IAuthorizationService;
import com.jsict.platform.service.IUserService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class PermissionsAssignToRoleAction extends BaseAction
{
    IUserService userService;

    IAuthorizationService authorizationService;

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
     * <p>
     * Description: The process
     * </p>
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws ApplicationException
     * @throws SystemException
     * @author: lgq
     * @update: 2008-11-14-下午05:30:17 [变更人姓名] [变更内容]
     */

    @Override
    protected ActionForward process(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
        throws ApplicationException, SystemException
    {

        PermissionsAssignToRoleForm permissionsAssignToRoleForm = (PermissionsAssignToRoleForm) form;
        String actionType = permissionsAssignToRoleForm.getActionType();
        String roleId = request.getParameter("roleId");
        String platform = request.getParameter("platform");
        List<PlatformDomain> allPlatforms = getAllPlatforms();
        if(StringUtils.isBlank(platform))
            platform = allPlatforms.get(0).getId();

        if(Consts.ACTION_TYPE_EDIT.equals(actionType))
        {
            List<PermissionDomain> rootPermissionsForSpecificPlatform = authorizationService
                    .getRootPermissionsForSpecificPlatformAndRole(platform,
                        Long.parseLong(roleId));
            RoleDomain roleDomain = authorizationService
                    .getRoleWithoutPermissions(Long.parseLong(roleId));

            permissionsAssignToRoleForm
                    .setPermission(rootPermissionsForSpecificPlatform);

            permissionsAssignToRoleForm.setPlatforms(allPlatforms);
            permissionsAssignToRoleForm.setCurrentPlatformId(platform);
            permissionsAssignToRoleForm.setRoleDomain(roleDomain);
            request.getSession().setAttribute("permissionsAssignToRoleForm",
                permissionsAssignToRoleForm);
            permissionsAssignToRoleForm.setPermissionsString(JSONArray
                    .fromObject(rootPermissionsForSpecificPlatform).toString());

            return mapping.getInputForward();
        }
        else if(Consts.ACTION_TYPE_SAVE.equals(actionType))
        {
            //save one permission
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String url = request.getParameter("url");
            String description = request.getParameter("description");

            //TODO save permission
        }

        return null;
    }

    public List<PlatformDomain> getAllPlatforms()
    {
        PlatformDomain platformDomain1 = new PlatformDomain();
        platformDomain1.setId("95");
        platformDomain1.setName("电子交易");

        PlatformDomain platformDomain2 = new PlatformDomain();
        platformDomain2.setId("96");
        platformDomain2.setName("物流配载");

        PlatformDomain platformDomain3 = new PlatformDomain();
        platformDomain3.setId("97");
        platformDomain3.setName("仓储管理");

        PlatformDomain platformDomain4 = new PlatformDomain();
        platformDomain4.setId("98");
        platformDomain4.setName("信息门户");

        PlatformDomain platformDomain5 = new PlatformDomain();
        platformDomain5.setId("99");
        platformDomain5.setName("公共平台");

        return Arrays.asList(new PlatformDomain[]{platformDomain1,
                platformDomain2, platformDomain3, platformDomain4,
                platformDomain5});
    }
}

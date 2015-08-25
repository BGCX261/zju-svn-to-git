/**
 * RoleAssignToUserAction.java 2008-11-13 下午03:17:24 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.action;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.action.BaseAction;
import com.jsict.base.context.ProjectContext;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.platform.domain.RoleDomain;
import com.jsict.platform.domain.UserAccountDomain;
import com.jsict.platform.form.RoleAssignToUserForm;
import com.jsict.platform.service.IAuthorizationService;
import com.jsict.platform.service.IUserService;
import com.jsict.platform.util.SecurityUtils;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class RoleAssignToUserAction extends BaseAction
{
    private IAuthorizationService authorizationService;

    private IUserService userService;

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
     * @update: 2008-11-13-下午03:17:24 [变更人姓名] [变更内容]
     */

    @Override
    protected ActionForward process(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
        throws ApplicationException, SystemException
    {
        RoleAssignToUserForm roleAssignToUserForm = (RoleAssignToUserForm) form;
        String actionType = roleAssignToUserForm.getActionType();

        if(Consts.ACTION_TYPE_EDIT.equals(actionType))
        {
            //显示该用户的权限
            Serializable userAccountId = Long.parseLong(request
                    .getParameter("userAccountId"));
            UserAccountDomain userAccountDomain = userService
                    .getUserAccountWithoutRoles(userAccountId);
            List<RoleDomain> allRolesByUserWithItemsSelected = authorizationService
                    .getRolesByUserWithItemsSelectedViaOp(userAccountId,
                        SecurityUtils.getUserId(), ProjectContext
                                .getSubsystemCode());
            roleAssignToUserForm.setUser(userAccountDomain);
            roleAssignToUserForm.setRoleList(allRolesByUserWithItemsSelected);
            request.getSession().setAttribute("roleAssignToUserForm",
                roleAssignToUserForm);
            request.getSession().setAttribute(
                "allRolesByUserWithItemsSelected",
                allRolesByUserWithItemsSelected);
            return mapping.getInputForward();
        }
        else if(Consts.ACTION_TYPE_SAVE.equals(actionType))
        {
            //save roles
            String[] selectedRoles = request
                    .getParameterValues("selectedRoles");
            String userAccountId = request.getParameter("userAccountId");
            authorizationService.updateParticialUserRoles(userAccountId,
                SecurityUtils.getUserId(), ProjectContext.getSubsystemCode(),
                selectedRoles);
            List<RoleDomain> allRolesByUserWithItemsSelected = authorizationService
                    .getAllRolesByUserWithItemsSelected(userAccountId);
            roleAssignToUserForm.setRoleList(allRolesByUserWithItemsSelected);
            request.getSession().setAttribute(
                "allRolesByUserWithItemsSelected",
                allRolesByUserWithItemsSelected);
            request.setAttribute("saveOk", "true");
            return mapping.getInputForward();
        }
        else
        {
            return mapping.getInputForward();
        }
    }
}

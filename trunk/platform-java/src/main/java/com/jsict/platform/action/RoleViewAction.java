package com.jsict.platform.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.action.BaseAction;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.platform.domain.RoleDomain;
import com.jsict.platform.form.RoleViewForm;
import com.jsict.platform.service.IAuthorizationService;
import com.jsict.platform.service.IUserService;

public class RoleViewAction extends BaseAction
{
    IUserService userService;

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

    @Override
    protected ActionForward process(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
        throws NumberFormatException, ApplicationException, SystemException
    {
        RoleViewForm roleForm = (RoleViewForm) form;
        String actionType = roleForm.getActionType();

        if(Consts.ACTION_TYPE_VIEW.equals(actionType))
        {
            String userAccountId = request.getParameter("roleId");
            RoleDomain roleDomain = authorizationService
                    .getRoleWithoutPermissions(Long.parseLong(userAccountId));

            roleForm.setRoleDomain(roleDomain);
            roleForm.setActionType(actionType);

            request.setAttribute("roleForm", roleForm);
            request.setAttribute("roleDomain", roleDomain);

            return mapping.getInputForward();
        }
        else
        {
            return mapping.getInputForward();
        }
    }
}

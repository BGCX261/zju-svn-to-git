package com.jsict.platform.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.action.BaseAction;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.platform.domain.RoleDomain;
import com.jsict.platform.form.RoleForm;
import com.jsict.platform.service.IAuthorizationService;
import com.jsict.platform.service.IUserService;

public class RoleAddEditAction extends BaseAction
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
        RoleForm roleForm = (RoleForm) form;
        String actionType = roleForm.getActionType();

        if(Consts.ACTION_TYPE_NEW.equals(actionType))
        {
            RoleDomain roleDomain = new RoleDomain();

            roleForm.setRoleDomain(roleDomain);
            roleForm.setActionType(actionType);

            request.getSession().setAttribute("roleForm", roleForm);
            request.getSession().setAttribute("roleDomain", roleDomain);

            return mapping.getInputForward();
        }
        else if(Consts.ACTION_TYPE_EDIT.equals(actionType))
        {
            String userAccountId = request.getParameter("roleId");
            RoleDomain roleDomain = authorizationService
                    .getRoleWithPermissions(Long.parseLong(userAccountId));
            roleForm.setRoleDomain(roleDomain);
            roleForm.setActionType(actionType);

            request.getSession().setAttribute("roleForm", roleForm);
            request.getSession().setAttribute("roleDomain", roleDomain);

            return mapping.getInputForward();
        }
        else if(Consts.ACTION_TYPE_SAVE.equals(actionType))
        {
            RoleDomain roleDomain = roleForm.getRoleDomain();
            String mode = StringUtils.isBlank(roleDomain.getId()) ? "new"
                    : "edit";
            if("new".equals(mode))
            {
                //新建用户，设置创建时间
                SimpleDateFormat sdf = new SimpleDateFormat(
                    Consts.DATE_TIME_FORMAT);
                roleDomain.setCreatedDate(sdf.format(new Date()));
                authorizationService.saveRole(roleDomain);
            }
            else
            {
                authorizationService.updateRole(roleDomain);
            }

            return new ActionForward(mapping.findForward("success"));
        }
        else if(Consts.ACTION_TYPE_DELETE.equals(actionType))
        {
            String roleId = request.getParameter("roleId");
            RoleDomain roleDomain = new RoleDomain();
            roleDomain.setId(roleId);
            authorizationService.deleteRole(roleDomain);

            return new ActionForward(mapping.findForward("success"));
        }
        else
        {
            return mapping.getInputForward();
        }
    }
}

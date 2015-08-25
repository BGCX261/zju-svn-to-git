package com.jsict.platform.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.action.BaseAction;
import com.jsict.base.context.ProjectContext;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.base.util.StringUtil;
import com.jsict.platform.constants.CodeKey;
import com.jsict.platform.domain.CompanyDomain;
import com.jsict.platform.domain.RoleDomain;
import com.jsict.platform.domain.SubsystemDomain;
import com.jsict.platform.domain.UserAccountDomain;
import com.jsict.platform.form.UserAccountForm;
import com.jsict.platform.service.IAuthorizationService;
import com.jsict.platform.service.ICompanyService;
import com.jsict.platform.service.IUserService;
import com.jsict.platform.util.SecurityUtils;
import com.jsict.platform.util.UIHelper;

public class UserAccountAddEditAction extends BaseAction
{
    IUserService userService;

    ICompanyService companyService;

    IAuthorizationService authorizationService;

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
     * @param companyService
     *            The companyService to set.
     */
    @Required
    public void setCompanyService(ICompanyService companyService)
    {
        this.companyService = companyService;
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

    private void setRoleOptionsAndCheck(UserAccountForm userAccountForm,
            String[] assignedRoles) throws ApplicationException,
        SystemException
    {
        //检查用户是否有权限指定assignedRoles

        if(assignedRoles != null && assignedRoles.length > 0)
        {
            List<RoleDomain> roleOptions = new ArrayList<RoleDomain>();
            for (String roleName : assignedRoles)
            {
                if(StringUtil.isNullString(roleName))
                {
                    continue;
                }
                RoleDomain roleDomain = authorizationService
                        .getRoleWithoutPermissionsByBCode(roleName);
                roleOptions.add(roleDomain);
                if(authorizationService.canAssignedRole(roleDomain.getId()) == false)
                {
                    //                    ProjectContext.getErrorList().add(
                    //                        new MessageInfo("P006", new String[]{"",
                    //                                roleDomain.getName()}));
                    //                    throw new ApplicationException();
                }
            }

            userAccountForm.setRoleOptions(roleOptions);
        }
    }

    @Override
    protected ActionForward process(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
        throws NumberFormatException, ApplicationException, SystemException
    {
        UserAccountForm userAccountForm = (UserAccountForm) form;
        String actionType = userAccountForm.getActionType();
        String subsystemCode = request.getParameter("subsystemCode");
        String[] assignedRoles = request.getParameterValues("assignedRoles") == null ? new String[0]
                : request.getParameterValues("assignedRoles");

        String fixedCompanyId = request.getParameter("fixedCompanyId");

        //固定公司列表 
        if(StringUtils.isBlank(fixedCompanyId) == false)
        {
            CompanyDomain fixedCompanyDomain = companyService.getCompany(Long
                    .parseLong(fixedCompanyId));

            userAccountForm.setFixedCompany(fixedCompanyDomain);
        }

        //默认为99(公共平台)
        if(StringUtils.isBlank(subsystemCode))
            subsystemCode = "99";

        if(Consts.ACTION_TYPE_NEW.equals(actionType))
        {
            UserAccountDomain userAccountDomain = new UserAccountDomain();

            userAccountDomain.setCompanyDomain(new CompanyDomain());

            userAccountForm.setUserAccountDomain(userAccountDomain);
            userAccountForm.setActionType(actionType);
            //填充公司列表
            userAccountForm
                    .setCompanyOptions(UIHelper
                            .getCompanyOptions(companyService
                                    .getAvailableCompanyList()));
            userAccountDomain.setEditMode(actionType);
            request.getSession().setAttribute("userAccountForm",
                userAccountForm);
            request.getSession().setAttribute("userAccountDomain",
                userAccountDomain);
            request.setAttribute("subsystemCode", subsystemCode);
            setRoleOptionsAndCheck(userAccountForm, assignedRoles);
            return mapping.getInputForward();
        }
        else if(Consts.ACTION_TYPE_EDIT.equals(actionType))
        {
            String userAccountId = request.getParameter("userAccountId");

            UserAccountDomain userAccountDomain = userService
                    .getUserAccountWithoutRoles(Long.parseLong(userAccountId));
            userAccountForm.setUserAccountDomain(userAccountDomain);
            userAccountForm.setActionType(actionType);
            //填充角色列表
            List<RoleDomain> allRolesByUserWithItemsSelected = authorizationService
                    .getRolesByUserWithItemsSelectedViaOp(userAccountId,
                        SecurityUtils.getUserId(), ProjectContext
                                .getSubsystemCode());

            userAccountForm.setRoleOptions(allRolesByUserWithItemsSelected);
            //填充公司列表
            userAccountForm
                    .setCompanyOptions(UIHelper
                            .getCompanyOptions(companyService
                                    .getAvailableCompanyList()));
            userAccountDomain.setEditMode(actionType);
            request.getSession().setAttribute("userAccountForm",
                userAccountForm);
            request.getSession().setAttribute("userAccountDomain",
                userAccountForm.getUserAccountDomain());
            request.setAttribute("subsystemCode", subsystemCode);
            setRoleOptionsAndCheck(userAccountForm, assignedRoles);
            return mapping.getInputForward();
        }
        else if(Consts.ACTION_TYPE_SAVE.equals(actionType))
        {
            UserAccountDomain userAccountDomain = userAccountForm
                    .getUserAccountDomain();

            String mode = StringUtils.isBlank(userAccountDomain.getId()) ? "new"
                    : "edit";

            if("new".equals(mode))
            {

                userAccountDomain.setStatus(CodeKey.USER_STATUS_AVAILABLE);

                //新建用户，设置创建时间
                SimpleDateFormat sdf = new SimpleDateFormat(
                    Consts.DATE_TIME_FORMAT);
                userAccountDomain.setCreatedDate(sdf.format(new Date()));
                SubsystemDomain subsystem = new SubsystemDomain();
                subsystem.setId(subsystemCode);

                userAccountDomain.setSubsystem(subsystem);

                UserAccountDomain domain = userService.save(userAccountDomain);

                assignRoles(domain.getId(), request
                        .getParameterValues("selectedRoles"));
            }
            else
            {
                if(userAccountDomain.getSubsystem() == null)
                {
                    SubsystemDomain subsystem = new SubsystemDomain();
                    subsystem.setId(subsystemCode);

                    userAccountDomain.setSubsystem(subsystem);
                }
                userService.update(userAccountDomain);
                if(request.getParameterValues("selectedRoles") != null)
                    assignRoles(userAccountDomain.getId(), request
                            .getParameterValues("selectedRoles"));
            }

            return new ActionForward(mapping.findForward("success"));
        }
        else if("resetPassword".equals(actionType))
        {//重置密码
            String userAccountId = request.getParameter("userAccountId");
            userService.resetPassword(userAccountId);
            return null;
        }
        else
        {
            return mapping.getInputForward();
        }
    }

    private void assignRoles(Serializable userId, String[] assignedRoles)
        throws ApplicationException, SystemException
    {
        if(assignedRoles == null)
            assignedRoles = new String[0];

        //        List<Serializable> roles = new ArrayList<Serializable>();
        //        for (String roleName : assignedRoles)
        //        {
        //            RoleDomain roleDomain = authorizationService
        //                    .getRoleWithoutPermissionsByBCode(roleName);
        //            roles.add(roleDomain.getId());
        //        }
        authorizationService.updateParticialUserRoles(userId, SecurityUtils
                .getUserId(), ProjectContext.getSubsystemCode(), assignedRoles);

    }
}

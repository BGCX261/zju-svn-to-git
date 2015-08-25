package com.jsict.platform.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.action.BaseListAction;
import com.jsict.base.context.ProjectContext;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.base.util.tags.SelectItem;
import com.jsict.platform.domain.RoleDomain;
import com.jsict.platform.domain.UserAccountDomain;
import com.jsict.platform.form.UserAccountListForm;
import com.jsict.platform.service.IAuthorizationService;
import com.jsict.platform.service.ICompanyService;
import com.jsict.platform.service.IUserService;
import com.jsict.platform.util.SecurityUtils;
import com.jsict.platform.util.UIHelper;

public class UserAccountListAction extends BaseListAction
{
    IUserService userService;

    ICompanyService companyService;

    IAuthorizationService authorizationService;

    private UserAccountDomain userAccountDomain;

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
    public ActionForward doListProcess(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
        throws ApplicationException, SystemException
    {
        UserAccountListForm userAccountListForm = (UserAccountListForm) form;
        String actionType = userAccountListForm.getActionType();
        System.out.println(userAccountDomain.isCanEdit());

        if(Consts.ACTION_TYPE_SELECT.equals(actionType)
                || Consts.ACTION_TYPE_INIT.equals(actionType)
                || StringUtils.isBlank(actionType))
        {
            return doListExecute(mapping, form, request, response);

        }

        else if(Consts.ACTION_TYPE_CANCEL.equals(actionType))
        {
            return new ActionForward(mapping.findForward(actionType));
        }
        else if(Consts.ACTION_TYPE_DELETE.equals(actionType))
        {
            // TODO set the consignation flag to 0
            String userAccountId = request.getParameter("userAccountId");
            userService.disableUser(Long.parseLong(userAccountId));

            return doListExecute(mapping, form, request, response);
        }
        else if("unfreeze".equals(actionType))
        {
            String userAccountId = request.getParameter("userAccountId");
            userService.enableUser(Long.parseLong(userAccountId));

            return doListExecute(mapping, form, request, response);
        }
        else if("freeze".equals(actionType))
        {
            String userAccountId = request.getParameter("userAccountId");
            userService.freezeUser(Long.parseLong(userAccountId));

            return doListExecute(mapping, form, request, response);
        }
        else if("back".equals(actionType))
        {
            ActionForward actionForward = new ActionForward(mapping
                    .findForward(actionType).getPath());
            actionForward.setRedirect(true);
            return actionForward;
        }
        else
        {
            return mapping.getInputForward();
        }

    }

    public ActionForward doListExecute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
        throws ApplicationException, SystemException
    {
        UserAccountListForm userAccountListForm = (UserAccountListForm) form;
        String roleId = request.getParameter("roleId");

        // TODO 查询所有的托运客户，设置到form中的selectitems
        getEntityFilter().addOrder("loginId");
        List<UserAccountDomain> userAccountList;

        //只查询该用户创建的用户

        //预选择公司列表
        String fixedCompanyId = request.getParameter("fixedCompanyId");
        if(StringUtils.isBlank(fixedCompanyId) == false)
        {
            userAccountListForm.setFixedCompany(companyService.getCompany(Long
                    .parseLong(fixedCompanyId)));

        }

        String assignedRoles = request.getParameter("assignedRoles");
        if(StringUtils.isBlank(assignedRoles) == false)
        {
            userAccountListForm.setAssignedRoles(assignedRoles);
        }

        if(userAccountListForm.getFixedCompany() != null)
        {
            getEntityFilter().removeFilter("company.id");
            getEntityFilter().addFilter("company.id",
                Long.parseLong(userAccountListForm.getFixedCompany().getId()));

        }

        if(StringUtils.isBlank(roleId))
        {
            userAccountList = userService.getOwnUserAccountListWithRoles(
                getEntityFilter(), getPageNo(), getPageSize());
        }
        else
        {
            //            userAccountList = userService.getUserAccountListByRole(Long
            //                    .parseLong(roleId));
            String companyId = request
                    .getParameter("filter_company.id_long_eq");
            if(StringUtils.isBlank(companyId)
                    && userAccountListForm.getFixedCompany() != null)
            {
                companyId = userAccountListForm.getFixedCompany().getId();
            }
            String userName = request.getParameter("filter_name_like");
            userAccountList = userService.getOwnUserAccountListByRole(Long
                    .parseLong(roleId), companyId, userName);

        }

        userAccountListForm.setResultList(userAccountList);
        // 填充公司列表
        userAccountListForm.setCompanyOptions(UIHelper
                .getCompanyOptions(companyService.getAvailableCompanyList()));
        EntityFilter tf = new EntityFilter();
        tf.addOrder("name");
        List<RoleDomain> roleDomains;

        //填充role下拉框
        if(SecurityUtils.hasAdminRole())
            roleDomains = authorizationService.getRoleList(tf, null, null);
        else
            roleDomains = authorizationService.getAssignableRolesByUserId(
                SecurityUtils.getUserId(), ProjectContext.getSubsystemCode());

        List<SelectItem> roleOptions = UIHelper.getRoleOptions(roleDomains);

        roleOptions.add(new SelectItem("未指定", "-1"));
        userAccountListForm.setRoleOptions(roleOptions);

        if(userAccountList instanceof PagedList)
            setPageInfo(request, (PagedList) userAccountList);

        return mapping.getInputForward();
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
     * @param userAccountDomain
     *            The userAccountDomain to set.
     */
    public void setUserAccountDomain(UserAccountDomain userAccountDomain)
    {
        this.userAccountDomain = userAccountDomain;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return UserAccountDomain userAccountDomain.
     */
    public UserAccountDomain getUserAccountDomain()
    {
        return userAccountDomain;
    }

}

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
import com.jsict.platform.domain.UserAccountDomain;
import com.jsict.platform.form.UserAccountViewForm;
import com.jsict.platform.service.ICompanyService;
import com.jsict.platform.service.IUserService;
import com.jsict.platform.util.IPasswordGenerator;

public class UserAccountViewAction extends BaseAction
{
    IUserService userService;

    ICompanyService companyService;

    IPasswordGenerator passwordGenerator;

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param passwordGenerator
     *            The passwordGenerator to set.
     */
    @Required
    public void setPasswordGenerator(IPasswordGenerator passwordGenerator)
    {
        this.passwordGenerator = passwordGenerator;
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

    @Override
    protected ActionForward process(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
        throws NumberFormatException, ApplicationException, SystemException
    {
        UserAccountViewForm userAccountViewForm = (UserAccountViewForm) form;
        String actionType = userAccountViewForm.getActionType();

        if(Consts.ACTION_TYPE_VIEW.equals(actionType))
        {
            String userAccountId = request.getParameter("userAccountId");
            UserAccountDomain userAccountDomain = userService
                    .getUserAccountWithoutRoles(Long.parseLong(userAccountId));
            userAccountViewForm.setUserAccountDomain(userAccountDomain);
            userAccountViewForm.setActionType(actionType);

            request.setAttribute("userAccountForm", userAccountViewForm);
            request.setAttribute("userAccountDomain", userAccountDomain);

            return mapping.getInputForward();
        }
        else
        {
            return mapping.getInputForward();
        }
    }
}

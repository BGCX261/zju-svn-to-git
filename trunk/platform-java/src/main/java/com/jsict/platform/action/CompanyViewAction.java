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
import com.jsict.platform.domain.CompanyDomain;
import com.jsict.platform.form.CompanyViewForm;
import com.jsict.platform.service.ICompanyService;

public class CompanyViewAction extends BaseAction
{
    ICompanyService companyService;

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

    @Override
    protected ActionForward process(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
        throws NumberFormatException, ApplicationException, SystemException
    {
        CompanyViewForm companyForm = (CompanyViewForm) form;
        String actionType = companyForm.getActionType();

        if(Consts.ACTION_TYPE_VIEW.equals(actionType))
        {
            String userAccountId = request.getParameter("companyId");
            CompanyDomain companyDomain = companyService.getCompany(Long
                    .parseLong(userAccountId));
            companyForm.setActionType(actionType);
            companyForm.setCompanyDomain(companyDomain);

            request.setAttribute("companyForm", companyForm);
            request.setAttribute("companyDomain", companyDomain);

            return mapping.getInputForward();
        }
        else
        {
            return mapping.getInputForward();
        }
    }
}

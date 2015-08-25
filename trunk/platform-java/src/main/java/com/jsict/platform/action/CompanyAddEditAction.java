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
import com.jsict.platform.constants.CodeKey;
import com.jsict.platform.domain.CompanyDomain;
import com.jsict.platform.form.CompanyForm;
import com.jsict.platform.service.ICompanyService;

public class CompanyAddEditAction extends BaseAction
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
        CompanyForm companyForm = (CompanyForm) form;
        String actionType = companyForm.getActionType();

        if(Consts.ACTION_TYPE_NEW.equals(actionType))
        {
            CompanyDomain companyDomain = new CompanyDomain();

            companyForm.setActionType(actionType);
            companyForm.setCompanyDomain(companyDomain);

            request.getSession().setAttribute("companyForm", companyForm);
            request.getSession().setAttribute("companyDomain",
                companyForm.getCompanyDomain());

            return mapping.getInputForward();
        }
        else if(Consts.ACTION_TYPE_EDIT.equals(actionType))
        {
            String userAccountId = request.getParameter("companyId");
            CompanyDomain companyDomain = companyService.getCompany(Long
                    .parseLong(userAccountId));

            companyForm.setActionType(actionType);
            companyForm.setCompanyDomain(companyDomain);

            request.getSession().setAttribute("companyForm", companyForm);
            request.getSession().setAttribute("companyDomain", companyDomain);

            return mapping.getInputForward();
        }
        else if(Consts.ACTION_TYPE_SAVE.equals(actionType))
        {
            CompanyDomain companyDomain = companyForm.getCompanyDomain();
            String mode = StringUtils.isBlank(companyDomain.getId()) ? "new"
                    : "edit";
            if("new".equals(mode))
            {
                companyDomain.setStatus(CodeKey.COMPANY_STATUS_AVAILABLE);

                //新建用户，设置创建时间
                SimpleDateFormat sdf = new SimpleDateFormat(
                    Consts.DATE_TIME_FORMAT);
                companyDomain.setCreatedDate(sdf.format(new Date()));
                companyService.save(companyDomain);

            }
            else
            {
                companyService.update(companyDomain);

            }

            return new ActionForward(mapping.findForward("success"));
        }
        else
        {
            return mapping.getInputForward();
        }
    }
}

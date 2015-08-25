package com.jsict.platform.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jsict.base.action.BaseListAction;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.base.util.Op;
import com.jsict.platform.domain.CompanyDomain;
import com.jsict.platform.form.CompanyListForm;
import com.jsict.platform.service.ICompanyService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class CompanyListAction extends BaseListAction
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
    public void setCompanyService(ICompanyService companyService)
    {
        this.companyService = companyService;
    }

    @Override
    public ActionForward doListProcess(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
        throws ApplicationException, SystemException
    {
        CompanyListForm companyListForm = (CompanyListForm) form;
        String actionType = companyListForm.getActionType();

        if(Consts.ACTION_TYPE_SELECT.equals(actionType)
                || Consts.ACTION_TYPE_INIT.equals(actionType)
                || StringUtils.isBlank(actionType))
        {
            try
            {
                return doListExecute(mapping, form, request, response);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }

        else if(Consts.ACTION_TYPE_CANCEL.equals(actionType))
        {
            return new ActionForward(mapping.findForward(actionType));
        }
        else if(Consts.ACTION_TYPE_DELETE.equals(actionType))
        {
            //TODO set the consignation flag to 0
            String companyId = request.getParameter("companyId");
            companyService.disableCompany(Long.parseLong(companyId));
            return new ActionForward(mapping.findForward(actionType));
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
        CompanyListForm companyListForm = (CompanyListForm) form;

        //TODO 查询所有的托运客户，设置到form中的selectitems
        getEntityFilter().addOrder("code");
        
        PagedList<CompanyDomain> companyList = companyService.getCompanyList(
            getEntityFilter(), getPageNo(), getPageSize());

        companyListForm.setResultList(companyList);

        setPageInfo(request, (PagedList) companyList);

        return mapping.getInputForward();
    }
}

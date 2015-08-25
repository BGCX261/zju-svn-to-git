/**
 * BusinessLogListAction.java 2008-11-26 上午09:55:28 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.action;

import java.util.ArrayList;
import java.util.List;

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
import com.jsict.base.util.tags.SelectItem;
import com.jsict.platform.domain.BusinessLogDomain;
import com.jsict.platform.form.BusinessLogListForm;
import com.jsict.platform.service.IBusinessLogService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class BusinessLogListAction extends BaseListAction
{
    private IBusinessLogService businessLogService;

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param businessLogService
     *            The businessLogService to set.
     */
    public void setBusinessLogService(IBusinessLogService businessLogService)
    {
        this.businessLogService = businessLogService;
    }

    @Override
    public ActionForward doListProcess(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
        throws ApplicationException, SystemException
    {
        BusinessLogListForm businessLogListForm = (BusinessLogListForm) form;
        String actionType = businessLogListForm.getActionType();

        if(Consts.ACTION_TYPE_SELECT.equals(actionType)
                || Consts.ACTION_TYPE_INIT.equals(actionType)
                || StringUtils.isBlank(actionType))
        {
            List<BusinessLogDomain> businessLogList = businessLogService
                    .getBusinessLogList(getEntityFilter(), getPageNo(),
                        getPageSize(), true);
            businessLogListForm.setResultList(businessLogList);
            businessLogListForm.setSubSystems(getSubSystemOptions());
            request.setAttribute("businessLogListForm", businessLogListForm);
            if(businessLogList instanceof PagedList)
                setPageInfo(request, (PagedList) businessLogList);
            return mapping.getInputForward();
        }

        return mapping.getInputForward();
    }

    private List<SelectItem> getSubSystemOptions()
    {
        List<SelectItem> options = new ArrayList<SelectItem>();
        options.add(new SelectItem("公共平台", "platform"));
        options.add(new SelectItem("物流配载", "lca"));
        options.add(new SelectItem("仓储管理", "wms"));
        options.add(new SelectItem("电子交易", "etm"));
        options.add(new SelectItem("信息平台", "ptl"));
        return options;
    }
}

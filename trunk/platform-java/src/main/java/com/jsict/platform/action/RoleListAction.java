package com.jsict.platform.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.action.BaseListAction;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.platform.domain.RoleDomain;
import com.jsict.platform.form.RoleListForm;
import com.jsict.platform.service.IAuthorizationService;
import com.jsict.platform.service.IUserService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class RoleListAction extends BaseListAction
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
    public ActionForward doListProcess(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
        throws ApplicationException, SystemException
    {
        RoleListForm roleListForm = (RoleListForm) form;
        String actionType = roleListForm.getActionType();

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
            String roleId = request.getParameter("roleId");
            RoleDomain roleDomain = new RoleDomain();
            roleDomain.setId(roleId);
            authorizationService.deleteRole(roleDomain);

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
        RoleListForm roleListForm = (RoleListForm) form;

        //TODO 查询所有的托运客户，设置到form中的selectitems
        getEntityFilter().addOrder("name");
        PagedList<RoleDomain> roleList = authorizationService.getRoleList(
            getEntityFilter(), getPageNo(), getPageSize());

        roleListForm.setResultList(roleList);

        setPageInfo(request, (PagedList) roleList);

        return mapping.getInputForward();
    }

}

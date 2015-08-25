/**
 * ChangePwdAction.java 2008-12-24 上午11:26:19 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.action;

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
import com.jsict.platform.domain.UserAccountDomain;
import com.jsict.platform.service.IUserService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class ChangePwdAction extends BaseAction
{

    IUserService userService;

    @Required
    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }

    /**
     * <p>
     * Description: The process
     * </p>
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws ApplicationException
     * @throws SystemException
     * @author: lgq
     * @update: [updatedate] [changer][change description]
     */

    @Override
    protected ActionForward process(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
        throws ApplicationException, SystemException
    {
        // TODO Auto-generated method stub
        String actiontType = request.getParameter("actionType");
        if(Consts.ACTION_TYPE_SELECT.equals(actiontType)
                || StringUtils.isBlank(actiontType))
        {

            UserAccountDomain userAccountDomain = userService
                    .getUserAccountWithoutRoles(userService.getUserId());
            request.setAttribute("userAccountDomainPWD", userAccountDomain);

            return mapping.getInputForward();
        }
        else if(Consts.ACTION_TYPE_SAVE.equals(actiontType))
        {
            String userAccountId = request.getParameter("userAccountId");
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            userService.resetPassword(userService.getUserId(), oldPassword,
                newPassword);

            return mapping.findForward(Consts.ACTION_TYPE_SAVE);
        }

        return mapping.getInputForward();
    }
}

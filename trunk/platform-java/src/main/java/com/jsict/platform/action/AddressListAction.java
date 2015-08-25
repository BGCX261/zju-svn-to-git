package com.jsict.platform.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.action.BaseListAction;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.platform.domain.UserAccountDomain;
import com.jsict.platform.form.AddressListForm;
import com.jsict.platform.service.IUserService;

public class AddressListAction extends BaseListAction
{
    IUserService userService;

    /**
     * <p>
     * Description: The setUserService
     * </p>
     * 
     * @param userService
     * @author: lgq
     * @update: [updatedate] [changer][change description]
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
        
        AddressListForm addressListForm = (AddressListForm) form;
        String actionType = addressListForm.getActionType();
        if(Consts.ACTION_TYPE_INIT.equals(actionType)
                || Consts.ACTION_TYPE_SELECT.equals(actionType))
        {
            PagedList<UserAccountDomain> userAccountList = userService
                    .getUserAccountList(getEntityFilter(), getPageNo(),
                        getPageSize());
            addressListForm.setResultList(userAccountList);
            return mapping.getInputForward();
        }
        return mapping.getInputForward();
    }

}

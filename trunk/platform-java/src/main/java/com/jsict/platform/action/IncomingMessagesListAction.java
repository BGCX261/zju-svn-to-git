/**
 * MessagesListAction.java 2008-11-27 下午10:02:27 lgq 版权所有 (c) 2007-2008
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

import com.jsict.base.action.BaseListAction;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.ChineseUtil;
import com.jsict.base.util.Consts;
import com.jsict.platform.domain.MessagesDomain;
import com.jsict.platform.domain.UserAccountDomain;
import com.jsict.platform.domain.UserAddressDomain;
import com.jsict.platform.form.MessagesListForm;
import com.jsict.platform.service.IMessagesService;
import com.jsict.platform.service.IUserService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class IncomingMessagesListAction extends BaseListAction
{
    private IMessagesService messagesService;

    private IUserService userService;

    @Required
    public void setMessagesService(IMessagesService messagesService)
    {
        this.messagesService = messagesService;
    }

    @Required
    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }

    /**
     * <p>
     * Description: The doListProcess
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
    public ActionForward doListProcess(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
        throws ApplicationException, SystemException
    {
        
        MessagesListForm messagesListForm = (MessagesListForm) form;
        String actionType = messagesListForm.getActionType();

        //发送的消息？，接受的消息？

        if(Consts.ACTION_TYPE_SELECT.equals(actionType)
                || Consts.ACTION_TYPE_INIT.equals(actionType)
                || StringUtils.isBlank(actionType))
        {
            PagedList<MessagesDomain> resultList = new PagedList<MessagesDomain>();
            resultList = messagesService
                    .getIncomingMessagesByUserId(this.getUserId(),
                        getEntityFilter(), getPageNo(), getPageSize());

            messagesListForm.setResultList(resultList);

            //get user address
            PagedList<UserAddressDomain> addressLists = new PagedList<UserAddressDomain>();
            PagedList<UserAccountDomain> userAccountList = userService
                    .getUserAccountList(new EntityFilter(), null, null);
            for (UserAccountDomain userAccountDomain : userAccountList)
            {
                if(!userAccountDomain.getId().equals(
                    String.valueOf(this.messagesService.getUserId())))
                {
                    String firstPinyin = ChineseUtil
                            .getFirstPinyin(userAccountDomain.getName());
                    addressLists.add(new UserAddressDomain(firstPinyin,
                        userAccountDomain));
                }

            }

            if(resultList instanceof PagedList)
                setPageInfo(request, (PagedList) resultList);

            request.setAttribute("addressList", addressLists);
            request.setAttribute("messagesListForm", messagesListForm);

            request.setAttribute("unread", messagesService
                    .getUnreadMessagesAmount(messagesService.getUserId()));
            request.setAttribute("read", messagesService
                    .getReadMessagesAmount(messagesService.getUserId()));

            return mapping.getInputForward();
        }
        else
        {
            return mapping.getInputForward();
        }
    }
}

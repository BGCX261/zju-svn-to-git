/**
 * OutgoingMessagesList.java 2008-11-30 下午08:53:25 lgq 版权所有 (c) 2007-2008
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
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.platform.domain.MessagesDomain;
import com.jsict.platform.form.MessagesListForm;
import com.jsict.platform.service.IMessagesService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class OutgoingMessagesList extends BaseListAction
{

    private IMessagesService messagesService;

    @Required
    public void setMessagesService(IMessagesService messagesService)
    {
        this.messagesService = messagesService;
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
                    .getOutgoingMessagesByUserId(this.getUserId(),
                        getEntityFilter(), getPageNo(), getPageSize());

            messagesListForm.setResultList(resultList);
            if(resultList instanceof PagedList)
                setPageInfo(request, resultList);
            return mapping.getInputForward();
        }
        else
        {
            return mapping.getInputForward();
        }
    }
}

/**
 * IMessagesService.java 2008-11-27 下午04:06:52 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.service;

import java.io.Serializable;

import com.jsict.base.IBaseService;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.platform.domain.MessagesDomain;
import com.jsict.platform.entity.Messages;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public interface IMessagesService extends IBaseService
{

    int getDeletedMessagesAmount(Serializable userId);

    public MessagesDomain getMessages(Serializable messagesId)
        throws ApplicationException, SystemException;

    public int getReadMessagesAmount(Serializable userId);

    public int getUnreadMessagesAmount(Serializable userId);

    public void updateMessages(MessagesDomain messages) throws SystemException;

    public Messages setMessagesRead(Serializable messagesId);

    PagedList<MessagesDomain> getOutgoingMessagesByUserId(Serializable userId,
            EntityFilter entityFilter, Integer pageNo, Integer pageSize)
        throws ApplicationException, SystemException;

    PagedList<MessagesDomain> getIncomingMessagesByUserId(Serializable userId,
            EntityFilter entityFilter, Integer pageNo, Integer pageSize)
        throws ApplicationException, SystemException;

    Messages setMessagesDeletedByFrom(Serializable messagesId);

    Messages setMessagesDeletedByTo(Serializable messagesId);

    Messages setMessagesReplied(Serializable messagesId);

    void replyMessages(MessagesDomain messages,
            Serializable originalMessagesId, boolean saveSendMessage)
        throws SystemException;

    void newMessages(MessagesDomain messages, boolean saveSendMessage)
        throws SystemException;

    PagedList<MessagesDomain> getIncomingMessagesByCurrentUser(
            EntityFilter entityFilter, Integer pageNo, Integer pageSize)
        throws ApplicationException, SystemException;

    PagedList<MessagesDomain> getOutgoingMessagesByCurrentUser(
            EntityFilter entityFilter, Integer pageNo, Integer pageSize)
        throws ApplicationException, SystemException;

    int getDeletedMessagesAmountByCurrentUser();

    int getReadMessagesAmountByCurrentUser();

    int getUnreadMessagesAmountByCurrentUser();

    MessagesDomain getUnreadMessagesMaxId(Serializable userId)
        throws ApplicationException, SystemException;

}

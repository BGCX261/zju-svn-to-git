/**
 * IMessagesRepository.java 2008-11-27 下午04:05:15 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.repository;

import java.io.Serializable;

import com.jsict.base.IBaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.platform.entity.Messages;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public interface IMessagesRepository extends IBaseRepository
{

    public void saveMessages(Messages messages);

    public Messages getMessages(Serializable messagesId);

    public int getUnreadMessagesAmount(Serializable userId);

    public int getReadMessagesAmount(Serializable userId);

    public int getDeletedMessagesAmount(Serializable userId);

    public Messages setMessagesRead(Serializable messagesId);

    PagedList<Messages> getOutgoingMessagesByUserId(Serializable userId,
            EntityFilter entityFilter, Integer pageNo, Integer pageSize);

    PagedList<Messages> getIncomingMessagesByUserId(Serializable userId,
            EntityFilter entityFilter, Integer pageNo, Integer pageSize);

    void updateMessages(Messages messages);

    Messages setMessagesDeletedByFrom(Serializable messagesId);

    Messages setMessagesDeletedByTo(Serializable messagesId);

    Messages setMessagesReplied(Serializable messagesId);

    public Messages getUnreadMessagesMaxId(Serializable userId);

}

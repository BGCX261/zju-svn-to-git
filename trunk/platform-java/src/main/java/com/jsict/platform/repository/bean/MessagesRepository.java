/**
 * MessagesRepository.java 2008-11-27 下午04:06:07 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.repository.bean;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.BaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.platform.constants.CodeKey;
import com.jsict.platform.dao.IMessagesDao;
import com.jsict.platform.entity.Messages;
import com.jsict.platform.repository.IMessagesRepository;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class MessagesRepository extends BaseRepository implements
        IMessagesRepository
{
    public IMessagesDao messagesDao;

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param messagesDao
     *            The messagesDao to set.
     */
    @Required
    public void setMessagesDao(IMessagesDao messagesDao)
    {
        this.messagesDao = messagesDao;
    }

    public void saveMessages(Messages messages)
    {
        messagesDao.save(messages);
    }

    public void updateMessages(Messages messages)
    {
        messagesDao.save(messages);
    }

    public PagedList<Messages> getIncomingMessagesByUserId(Serializable userId,
            EntityFilter entityFilter, Integer pageNo, Integer pageSize)
    {
        return messagesDao.getIncomingMessagesByUserId(userId, entityFilter,
            pageNo, pageSize);
    }

    public PagedList<Messages> getOutgoingMessagesByUserId(Serializable userId,
            EntityFilter entityFilter, Integer pageNo, Integer pageSize)
    {
        return messagesDao.getOutgoingMessagesByUserId(userId, entityFilter,
            pageNo, pageSize);
    }

    public Messages getMessages(Serializable messagesId)
    {
        return messagesDao.get(messagesId);
    }

    public int getUnreadMessagesAmount(Serializable userId)
    {
        return messagesDao.getMessagesAmount(userId,
            CodeKey.PLF_MESSAGES_TYPE_UNREAD);
    }

    public int getReadMessagesAmount(Serializable userId)
    {
        return messagesDao.getMessagesAmount(userId,
            CodeKey.PLF_MESSAGES_TYPE_READ);
    }

    public int getDeletedMessagesAmount(Serializable userId)
    {
        return messagesDao.getMessagesAmount(userId,
            CodeKey.PLF_MESSAGES_TYPE_DELETED);
    }

    public Messages setMessagesRead(Serializable messagesId)
    {
        return setMessagesStatus(messagesId, CodeKey.PLF_MESSAGES_TYPE_READ);
    }

    public Messages setMessagesReplied(Serializable messagesId)
    {
        Messages messages = messagesDao.get(messagesId);
        messages.setReplyFlag(CodeKey.MESSAGES_REPLY_STATUS_ALREADY_REPLIED);
        return messagesDao.save(messages);
    }

    public Messages setMessagesDeletedByFrom(Serializable messagesId)
    {
        Messages messages = messagesDao.get(messagesId);
        messages.setFromDeleteFlag(CodeKey.MESSAGES_DELETE_INDICATOR_DELETED);
        if(CodeKey.MESSAGES_DELETE_INDICATOR_DELETED.equals(messages
                .getFromDeleteFlag()))
            messages.setStatus(CodeKey.PLF_MESSAGES_TYPE_DELETED);
        return messagesDao.save(messages);
    }

    public Messages setMessagesDeletedByTo(Serializable messagesId)
    {
        Messages messages = messagesDao.get(messagesId);
        messages.setToDeleteFlag(CodeKey.MESSAGES_DELETE_INDICATOR_DELETED);
        if(CodeKey.MESSAGES_DELETE_INDICATOR_DELETED.equals(messages
                .getToDeleteFlag()))
            messages.setStatus(CodeKey.PLF_MESSAGES_TYPE_DELETED);
        return messagesDao.save(messages);
    }

    private Messages setMessagesStatus(Serializable messagesId, String status)
    {
        Messages messages = messagesDao.get(messagesId);
        messages.setStatus(status);
        return messagesDao.save(messages);
    }

    public Messages getUnreadMessagesMaxId(Serializable userId)
    {
        // TODO Auto-generated method stub

        return messagesDao.getUnreadMessagesMaxId(userId);
    }
}

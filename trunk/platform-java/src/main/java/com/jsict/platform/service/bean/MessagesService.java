/**
 * MessagesService.java 2008-11-27 下午04:07:39 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.service.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.jsict.base.BaseService;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.ConvertUtil;
import com.jsict.base.util.ListUtil;
import com.jsict.platform.constants.CodeKey;
import com.jsict.platform.domain.CompanyDomain;
import com.jsict.platform.domain.MessagesDomain;
import com.jsict.platform.domain.UserAccountDomain;
import com.jsict.platform.entity.Messages;
import com.jsict.platform.repository.IMessagesRepository;
import com.jsict.platform.repository.IUserAccountRepository;
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
@Transactional
public class MessagesService extends BaseService implements IMessagesService
{
    public IMessagesRepository messagesRepository;

    private IUserService userService;

    private IUserAccountRepository userAccountRepository;

    @Required
    public void setUserAccountRepository(
            IUserAccountRepository userAccountRepository)
    {
        this.userAccountRepository = userAccountRepository;
    }

    @Required
    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param messagesRepository
     *            The messagesRepository to set.
     */
    @Required
    public void setMessagesRepository(IMessagesRepository messagesRepository)
    {
        this.messagesRepository = messagesRepository;
    }

    public int getDeletedMessagesAmount(Serializable userId)
    {
        return messagesRepository.getDeletedMessagesAmount(userId);
    }

    public int getDeletedMessagesAmountByCurrentUser()
    {
        return getDeletedMessagesAmount(this.getUserId());
    }

    public MessagesDomain getMessages(Serializable messagesId)
        throws ApplicationException, SystemException
    {
        Messages messages = messagesRepository.getMessages(messagesId);
        return ConvertMessagesE2D(messages);
    }

    public int getReadMessagesAmount(Serializable userId)
    {
        return messagesRepository.getReadMessagesAmount(userId);
    }

    public int getUnreadMessagesAmount(Serializable userId)
    {
        return messagesRepository.getUnreadMessagesAmount(userId);
    }

    public MessagesDomain getUnreadMessagesMaxId(Serializable userId)
        throws ApplicationException, SystemException
    {
        Messages messages = messagesRepository.getUnreadMessagesMaxId(userId);
        MessagesDomain messagesDomain = ConvertMessagesE2D(messages);
        messagesDomain.setContent("");
        return messagesDomain;
    }

    public int getReadMessagesAmountByCurrentUser()
    {
        return getReadMessagesAmount(this.getUserId());
    }

    public int getUnreadMessagesAmountByCurrentUser()
    {
        return getUnreadMessagesAmount(this.getUserId());
    }

    public PagedList<MessagesDomain> getIncomingMessagesByCurrentUser(
            EntityFilter entityFilter, Integer pageNo, Integer pageSize)
        throws ApplicationException, SystemException
    {
        return getIncomingMessagesByUserId(this.getUserId(), entityFilter,
            pageNo, pageSize);
    }

    public PagedList<MessagesDomain> getIncomingMessagesByUserId(
            Serializable userId, EntityFilter entityFilter, Integer pageNo,
            Integer pageSize) throws ApplicationException, SystemException
    {

        PagedList<Messages> messagesList = messagesRepository
                .getIncomingMessagesByUserId(userId, entityFilter, pageNo,
                    pageSize);
        PagedList<MessagesDomain> list = new PagedList<MessagesDomain>();
        for (Messages messages : messagesList)
        {
            list.add(ConvertMessagesE2D(messages));
        }
        ListUtil.clonePagedInfo(list, messagesList);
        return list;
    }

    public PagedList<MessagesDomain> getOutgoingMessagesByCurrentUser(
            EntityFilter entityFilter, Integer pageNo, Integer pageSize)
        throws ApplicationException, SystemException
    {

        return getOutgoingMessagesByUserId(this.getUserId(), entityFilter,
            pageNo, pageSize);
    }

    public PagedList<MessagesDomain> getOutgoingMessagesByUserId(
            Serializable userId, EntityFilter entityFilter, Integer pageNo,
            Integer pageSize) throws ApplicationException, SystemException
    {

        PagedList<Messages> messagesList = messagesRepository
                .getOutgoingMessagesByUserId(userId, entityFilter, pageNo,
                    pageSize);
        PagedList<MessagesDomain> list = new PagedList<MessagesDomain>();
        for (Messages messages : messagesList)
        {
            list.add(ConvertMessagesE2D(messages));
        }
        ListUtil.clonePagedInfo(list, messagesList);
        return list;
    }

    public void replyMessages(MessagesDomain messages,
            Serializable originalMessagesId, boolean saveSendMessage)
        throws SystemException
    {
        saveMessages(messages, originalMessagesId, saveSendMessage);
    }

    public void newMessages(MessagesDomain messages, boolean saveSendMessage)
        throws SystemException
    {
        saveMessages(messages, null, saveSendMessage);
    }

    private void saveMessages(MessagesDomain messages,
            Serializable originalMessagesId, boolean saveSendMessage)
        throws SystemException
    {
        Messages messages2 = new Messages();
        ConvertUtil.domain2entity(messages, messages2);
        messages2.setSendtime(new Date());
        messages2.setStatus(CodeKey.PLF_MESSAGES_TYPE_UNREAD);
        messages2.setUserAccountByToid(userAccountRepository
                .getUserAccount(messages.getUserAccountByToid().getId()));
        messages2.setUserAccountByFromid(userAccountRepository
                .getUserAccount(this.getUserId()));
        messages2.setReadnotice(CodeKey.MESSAGES_READNOTICE_NOTREQUIRED);
        messages2.setReplyFlag(CodeKey.MESSAGES_REPLY_STATUS_NOT_REPLIED);
        messages2
                .setFromDeleteFlag(saveSendMessage ? CodeKey.MESSAGES_DELETE_INDICATOR_NOTDELETED
                        : CodeKey.MESSAGES_DELETE_INDICATOR_DELETED);
        messages2.setToDeleteFlag(CodeKey.MESSAGES_DELETE_INDICATOR_NOTDELETED);
        if(originalMessagesId != null)
        {
            //置原来的messages为已回复
            setMessagesReplied(originalMessagesId);
        }
        messagesRepository.saveMessages(messages2);
    }

    public void updateMessages(MessagesDomain messages) throws SystemException
    {
        Messages messages2 = messagesRepository.getMessages(messages.getId());
        ConvertUtil.domain2entity(messages, messages2);
        messagesRepository.updateMessages(messages2);
    }

    public Messages setMessagesDeletedByFrom(Serializable messagesId)
    {
        return messagesRepository.setMessagesDeletedByFrom(messagesId);
    }

    public Messages setMessagesDeletedByTo(Serializable messagesId)
    {
        return messagesRepository.setMessagesDeletedByTo(messagesId);
    }

    public Messages setMessagesRead(Serializable messagesId)
    {
        return messagesRepository.setMessagesRead(messagesId);
    }

    public Messages setMessagesReplied(Serializable messagesId)
    {
        return messagesRepository.setMessagesReplied(messagesId);
    }

    private MessagesDomain ConvertMessagesE2D(Messages messages)
        throws ApplicationException, SystemException
    {

        MessagesDomain messagesDomain = new MessagesDomain();
        ConvertUtil.entity2domain(messages, messagesDomain);
        UserAccountDomain userFrom = new UserAccountDomain();
        UserAccountDomain userTo = new UserAccountDomain();

        ConvertUtil.entity2domain(messages.getUserAccountByFromid(), userFrom);
        ConvertUtil.entity2domain(messages.getUserAccountByToid(), userTo);

        CompanyDomain fromcompanyDomain = new CompanyDomain();
        CompanyDomain tocompanyDomain = new CompanyDomain();
        ConvertUtil.entity2domain(messages.getUserAccountByFromid()
                .getCompany(), fromcompanyDomain);
        ConvertUtil.entity2domain(messages.getUserAccountByToid().getCompany(),
            tocompanyDomain);
        userFrom.setCompanyDomain(fromcompanyDomain);
        userTo.setCompanyDomain(tocompanyDomain);

        messagesDomain.setUserAccountByFromid(userFrom);
        messagesDomain.setUserAccountByToid(userTo);
        return messagesDomain;
    }
}

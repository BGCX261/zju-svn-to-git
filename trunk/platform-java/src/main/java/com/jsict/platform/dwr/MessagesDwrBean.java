/**
 * MessagesDwrBean.java 2008-11-28 下午01:34:13 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dwr;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.platform.domain.MessagesDomain;
import com.jsict.platform.domain.MessagesDomainJSon;
import com.jsict.platform.domain.UserAccountDomain;
import com.jsict.platform.service.IMessagesService;
import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class MessagesDwrBean
{
    private IMessagesService messagesService;

    private CaptchaService captchaService;

    @Required
    public void setCaptchaService(CaptchaService captchaService)
    {
        this.captchaService = captchaService;
    }

    @Required
    public void setMessagesService(IMessagesService messagesService)
    {
        this.messagesService = messagesService;
    }

    public int getTotalMessagesAmount()
    {
        return getReadMessagesAmount() + getUnreadMessagesAmount();
    }

    public int getReadMessagesAmount()
    {
        return messagesService.getReadMessagesAmount(messagesService
                .getUserId());
    }

    public int getUnreadMessagesAmount()
    {

        return messagesService.getUnreadMessagesAmount(messagesService
                .getUserId());
    }

    public boolean setMessagesDeletedByTo(Long messagesId)
    {
        try
        {
            messagesService.setMessagesDeletedByTo(messagesId);
            return true;
        }
        catch (Throwable e)
        {

            return false;
        }
    }

    public boolean setMessagesDeletedByFrom(Long messagesId)
    {
        try
        {
            messagesService.setMessagesDeletedByFrom(messagesId);
            return true;
        }
        catch (Throwable a)
        {
            return false;
        }
    }

    public boolean setMessagesRead(Long messagesId)
    {
        try
        {
            messagesService.setMessagesRead(messagesId);
            return true;
        }
        catch (Throwable a)
        {
            return false;
        }
    }

    public boolean saveMessages(MessagesDomainJSon messages,
            Long originalMessagesId, HttpSession session)
        throws SystemException
    {
        try
        {
            String captchaCode = messages.getCaptchaCode();
            if(validateCatcha(session, captchaCode) == false)
                return false;
            MessagesDomain messagesDomain = new MessagesDomain();
            messagesDomain.setContent(messages.getContent());
            messagesDomain.setTitle(messages.getTitle());
            messagesDomain.setUserAccountByToid(new UserAccountDomain());
            messagesDomain.getUserAccountByToid().setId(messages.getToId());

            messagesService.replyMessages(messagesDomain, originalMessagesId,
                messages.isSaveSendMessage());
            return true;
        }
        catch (Throwable e)
        {

            return false;
        }
    }

    private boolean validateCatcha(HttpSession session, String captchaCode)
    {
        Boolean isResponseCorrect = Boolean.FALSE;
        //remenber that we need an id to validate!
        String captchaId = session.getId();
        //retrieve the response
        String response = captchaCode;
        // Call the Service method
        try
        {
            isResponseCorrect = captchaService.validateResponseForID(captchaId,
                response);
            return isResponseCorrect;
        }
        catch (CaptchaServiceException e)
        {
            //should not happen, may be thrown if the id is not valid
            return false;
        }

    }

    public MessagesDomain getUnreadMessagesMaxId() throws ApplicationException,
        SystemException
    {
        return messagesService.getUnreadMessagesMaxId(messagesService
                .getUserId());
    }
}

/**
 * MailSender.java 2008-11-12 下午05:05:20 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.base.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class MailManager implements IMailManager
{
    private JavaMailSender mailSender;

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param mailSender
     *            The mailSender to set.
     */
    @Required
    public void setMailSender(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    /**
     * <p>
     * Description: The sendPlainTextMail
     * </p>
     * 
     * @param to
     * @param subject
     * @param content
     * @author: lgq
     * @throws MessagingException
     * @update: 2008-11-12-下午05:19:12 [变更人姓名] [变更内容]
     */

    public void sendSSLMail(String to, String subject, String content)
        throws MessagingException
    {
        final MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        final JavaMailSender sender = mailSender;
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content);

        try
        {
            new Thread()
            {
                public void run()
                {
                    sender.send(mail);//发送邮件
                }
            }.start();

        }
        catch (MailException e)
        {
            e.printStackTrace();
        }
    }
}

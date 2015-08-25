package com.jsict.base.util;

import javax.mail.MessagingException;

public interface IMailManager
{

    public abstract void sendSSLMail(String to, String subject, String content)
        throws MessagingException;

}
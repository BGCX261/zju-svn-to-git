/**
 * MessagesDomainJSon.java 2008-11-28 下午02:26:30 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.domain;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class MessagesDomainJSon
{
    private String title;

    private String content;

    private String toId;

    private boolean saveSendMessage = true;

    private String captchaCode;

    public String getCaptchaCode()
    {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode)
    {
        this.captchaCode = captchaCode;
    }

    public boolean isSaveSendMessage()
    {
        return saveSendMessage;
    }

    public void setSaveSendMessage(boolean saveSendMessage)
    {
        this.saveSendMessage = saveSendMessage;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param title
     *            The title to set.
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param content
     *            The content to set.
     */
    public void setContent(String content)
    {
        this.content = content;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String content.
     */
    public String getContent()
    {
        return content;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param to
     *            The to to set.
     */
    public void setToId(String to)
    {
        this.toId = to;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String to.
     */
    public String getToId()
    {
        return toId;
    }
}

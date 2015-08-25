/**
 * WebConsts.java 2008-12-8 下午01:06:59 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.base.util;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class WebConsts
{
    private String casURL = null;

    private String casLoginURL = null;

    private String casLogoutURL = null;

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param casURL
     *            The casURL to set.
     */
    public void setCasURL(String casURL)
    {
        this.casURL = casURL;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String casURL.
     */
    public String getCasURL()
    {
        return casURL;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param casLoginURL
     *            The casLoginURL to set.
     */
    public void setCasLoginURL(String casLoginURL)
    {
        this.casLoginURL = casLoginURL;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String casLoginURL.
     */
    public String getCasLoginURL()
    {
        if(casLoginURL == null)
        {
            return casURL + "/login";
        }
        return casLoginURL;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param casLogoutURL
     *            The casLogoutURL to set.
     */
    public void setCasLogoutURL(String casLogoutURL)
    {
        this.casLogoutURL = casLogoutURL;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String casLogoutURL.
     */
    public String getCasLogoutURL()
    {
        if(casLogoutURL == null)
        {
            return casURL + "/logout";
        }
        return casLogoutURL;
    }

}

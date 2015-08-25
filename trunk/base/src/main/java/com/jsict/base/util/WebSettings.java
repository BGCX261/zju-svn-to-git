/**
 * WebSettings.java 2009-1-5 下午05:04:06 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.base.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class WebSettings
{

    public static String getSubsystemCode(HttpServletRequest request)
    {
        ServletContext servletContext = request.getSession()
                .getServletContext();
        WebParams webParams = (WebParams) servletContext
                .getAttribute("webParams");
        return webParams.getSubsystemCode();
    }
}

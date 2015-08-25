/**
 * HttpUtils.java 2008-12-8 下午02:25:31 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.base.util;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class HttpUtils
{
    public static String getLogoutURL(HttpServletRequest request)
    {
        return request.getScheme() + "://" + request.getServerName() + ":"
                + request.getLocalPort() + request.getContextPath() + "/"
                + "logout.jsp";
    }

    public static String getUserInfo(HttpServletRequest request)
    {
        return request.getScheme() + "://" + request.getServerName() + ":"
                + request.getLocalPort() + request.getContextPath() + "/"
                + "UserInfo.do";
    }

    public static String getEntryURL(HttpServletRequest request)
    {
        return request.getScheme() + "://" + request.getServerName() + ":"
                + request.getLocalPort() + request.getContextPath();
    }

    public static String getUrlWithoutQueryParameters(String url)
    {
        if(url != null)
            return url.replaceAll("\\?.*", "");
        return null;
    }

    public static String getUrlStringWithPlatformId(String platformId,
            String url)
    {
        return MessageFormat.format("/{0}{1}", platformId, url);
    }

}

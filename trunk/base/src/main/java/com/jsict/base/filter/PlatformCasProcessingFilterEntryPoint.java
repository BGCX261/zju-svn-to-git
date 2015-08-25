/**
 * PlatformCasProcessingFilterEntryPoint.java 2008-12-26 下午01:19:14 lgq 版权所有 (c)
 * 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.base.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.util.CommonUtils;
import org.springframework.security.AuthenticationException;
import org.springframework.security.ui.cas.CasProcessingFilterEntryPoint;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class PlatformCasProcessingFilterEntryPoint extends
        CasProcessingFilterEntryPoint
{
    private String[] paramsToPopulate = new String[0];

    public void setParamsToPopulate(String[] paramsToPopulate)
    {
        this.paramsToPopulate = paramsToPopulate;
    }

    @Override
    public void commence(ServletRequest servletRequest,
            ServletResponse servletResponse,
            AuthenticationException authenticationException)
        throws IOException, ServletException
    {
        // TODO Auto-generated method stub
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String urlEncodedService = CommonUtils.constructServiceUrl(null,
            response, this.getServiceProperties().getService(), null, "ticket",
            true);
        String redirectUrl = CommonUtils.constructRedirectUrl(this
                .getLoginUrl(), "service", urlEncodedService, this
                .getServiceProperties().isSendRenew(), false);
        for (String param : paramsToPopulate)
        {
            if(request.getParameter(param) != null)
            {
                redirectUrl += "&" + param + "=" + request.getParameter(param);
            }
        }

        response.sendRedirect(redirectUrl);
    }
}

/**
 * MenuCheckingSecurityFilter.java 2008-12-16 下午04:51:41 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.ui.FilterChainOrder;
import org.springframework.security.ui.SpringSecurityFilter;
import org.springframework.security.util.AntUrlPathMatcher;
import org.springframework.security.util.RedirectUtils;

import com.jsict.base.util.HttpUtils;
import com.jsict.base.util.ProjectSettings;
import com.jsict.platform.util.SecurityUtils;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class MenuCheckingSecurityFilter extends SpringSecurityFilter
{

    private String menuNotAllowedUrl;

    private Set<String> checkingUrls;

    private ProjectSettings projectSettings;

    private String[] bypassUrls = new String[0];

    @Required
    public void setProjectSettings(ProjectSettings projectSettings)
    {
        this.projectSettings = projectSettings;
    }

    @Required
    public void setMenuNotAllowedUrl(String menuNotAllowedUrl)
    {
        this.menuNotAllowedUrl = menuNotAllowedUrl;
    }

    @Required
    public void setCheckingUrls(Set<String> checkingUrls)
    {
        this.checkingUrls = checkingUrls;
    }

    @Override
    protected void doFilterHttp(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        // TODO Auto-generated method stub
        if(SecurityContextHolder.getContext().getAuthentication() != null)
        {
            //first check if user has at least one permission on the platform
            String platformId = String
                    .valueOf(projectSettings.getSubsSytemId());
            boolean hasPlatformPermission = SecurityUtils
                    .hasPlatformPermission(platformId);
            String requestURIWithoutContextPath = request.getRequestURI()
                    .replaceAll(request.getContextPath(), "");
            //check bypass urls
            for (String bypassUrl : bypassUrls)
            {
                AntUrlPathMatcher matcher = new AntUrlPathMatcher(true);
                if(matcher.pathMatchesUrl(bypassUrl,
                    requestURIWithoutContextPath))
                {
                    hasPlatformPermission = true;
                    break;
                }
            }

            if(!hasPlatformPermission)
            {
                sendRedirect(request, response, menuNotAllowedUrl);
                return;
            }

            String requestURIWithPlatformId = HttpUtils
                    .getUrlStringWithPlatformId(platformId,
                        requestURIWithoutContextPath);

            if(hasPermission(requestURIWithoutContextPath) == false
                    && checkingUrls.contains(requestURIWithPlatformId))
            {
                sendRedirect(request, response, menuNotAllowedUrl);
                return;
            }
        }

        chain.doFilter(request, response);

    }

    private boolean hasPermission(String requestURI)
    {
        // TODO Auto-generated method stub

        return SecurityUtils.hasPermission(requestURI);
    }

    public int getOrder()
    {
        // TODO Auto-generated method stub
        return FilterChainOrder.ANONYMOUS_FILTER;
    }

    /**
     * Allow subclasses to modify the redirection message.
     * 
     * @param request
     *            the request
     * @param response
     *            the response
     * @param url
     *            the URL to redirect to
     * 
     * @throws IOException
     *             in the event of any failure
     */
    protected void sendRedirect(HttpServletRequest request,
            HttpServletResponse response, String url) throws IOException
    {

        RedirectUtils.sendRedirect(request, response, url, false);
    }

    public void setBypassUrls(String[] bypassUrls)
    {
        this.bypassUrls = bypassUrls;
    }

}

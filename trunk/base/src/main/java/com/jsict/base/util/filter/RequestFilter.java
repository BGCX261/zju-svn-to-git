/**
 * RequestFilter.java        Sep 24, 2008 6:35:39 PM
 * Administrator
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.base.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 * <P>Description: The Class RequestFilter.</p>
 * 
 * @author Administrator
 * @version 1.0
 */
public class RequestFilter implements Filter
{
    
    private String encoding;
    
    /**
     * <p>Description: The destroy</p>
     * @author: Jinliang
     * @update: [updatedate] [changer][change description]
     */

    public void destroy()
    {
        

    }

    /**
     * <p>Description: 过滤器对request编码进行了统一过滤</p>
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     * @author: Jinliang
     * @update: [updatedate] [changer][change description]
     */

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        httpServletRequest.setCharacterEncoding(this.encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * <p>Description: 初始化信息,一般用来读取web.xml中的参数</p>
     * @param filterConfig
     * @throws ServletException
     * @author: Jinliang
     * @update: [updatedate] [changer][change description]
     */
    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.encoding = filterConfig.getInitParameter("encoding");
    }

}

package com.jsict.base.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.jsict.base.dao.EntityFilter;
import com.jsict.base.exception.FilterFormatNotValidException;

public class HttpRequestPaser
{
    private EntityFilter entityFilter;

    private Integer pageNo;

    private Integer pageSize;

    private Map<String, Object> params;

    public HttpRequestPaser(HttpServletRequest request)
        throws FilterFormatNotValidException
    {
        importFilterOrderFromRequest_bg(request);
    }

    private void importFilterOrderFromRequest_bg(HttpServletRequest request)
        throws FilterFormatNotValidException

    {
        /* import filter from the Request object */
        java.util.Enumeration e = request.getParameterNames();

        if(params != null)
        {
            params.clear();
        }
        params = new HashMap();
        String param = null;
        String value = null;
        while (e.hasMoreElements())
        {
            // parse param and value from request.

            param = (String) e.nextElement();
            value = request.getParameter(param);
            // put into the hashmap for further use.

            params.put(param, value);
        }

        EntityParser entityParser = new EntityParser();
        entityParser.parse(params);

        entityFilter = entityParser.getEntityFilter();

        if(entityFilter == null)
        {
            entityFilter = new EntityFilter();
        }
        pageNo = entityParser.getPageNo();
        pageSize = entityParser.getPageSize();
    }

    public String getEncodedValue(String origin)
        throws UnsupportedEncodingException
    {
        return new String(StringUtils.trimToEmpty(origin)
                .getBytes("ISO-8859-1"), "UTF-8");
    }

    public void setParameter(String name, Object value)
    {
        Map<String, Object> innerParams = getParams();
        if(innerParams.containsKey(name))
        {
            innerParams.remove(name);
            innerParams.put(name, value);
        }
        else
        {
            innerParams.put(name, value);
        }
    }

    public EntityFilter getEntityFilter()
    {
        return entityFilter;
    }

    public Integer getPageNo()
    {
        return pageNo;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public Map<String, Object> getParams()
    {
        return params;
    }

    public void setPageNo(Integer pageNo)
    {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }

}

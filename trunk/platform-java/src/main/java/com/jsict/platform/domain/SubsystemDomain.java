/**
 * SubsystemDomain.java 2008-12-27 下午11:17:33 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import com.jsict.base.BaseDomain;
import com.jsict.base.util.Text;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class SubsystemDomain extends BaseDomain
{
    // Fields    

    private String id;

    private String code;

    private String name;

    private String url;

    private Set<PermissionDomain> permissions = new LinkedHashSet<PermissionDomain>(
        0);

    @Text
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Text
    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    @Text
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Text
    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public Set<PermissionDomain> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(Set<PermissionDomain> permissions)
    {
        this.permissions = permissions;
    }

}

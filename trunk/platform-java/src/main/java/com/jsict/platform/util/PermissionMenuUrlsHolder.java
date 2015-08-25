/**
 * PermissionMenuUrlsHolder.java 2008-12-17 上午09:45:31 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.HttpUtils;
import com.jsict.platform.domain.PermissionDomain;
import com.jsict.platform.service.IAuthorizationService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class PermissionMenuUrlsHolder
{
    private Set<String> permissionUrls = new HashSet();

    IAuthorizationService authorizationService;

    @Required
    public void setAuthorizationService(
            IAuthorizationService authorizationService)
    {
        this.authorizationService = authorizationService;
    }

    public void init() throws ApplicationException, SystemException
    {
        List<PermissionDomain> permissions = authorizationService
                .getAllPermissions();
        for (PermissionDomain permissionDomain : permissions)
        {
            String url = HttpUtils
                    .getUrlWithoutQueryParameters(permissionDomain.getUrl());
            if(StringUtils.isBlank(url))
            {
                if(!permissionUrls.contains(url))
                {
                    permissionUrls.add(url);
                }
            }
        }
    }

    public Set<String> getPermissionUrls()
    {
        return permissionUrls;
    }

}

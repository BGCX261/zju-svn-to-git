/**
 * ProjectSettings.java 2008-12-22 下午02:20:10 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.base.util;

import org.springframework.beans.factory.annotation.Required;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class ProjectSettings
{

    private String subsystemName;

    private String subsSytemId;

    public String getSubsystemName()
    {
        return subsystemName;
    }

    @Required
    public void setSubsystemName(String subsystemName)
    {
        this.subsystemName = subsystemName;
    }

    public String getSubsSytemId()
    {
        return subsSytemId;
    }

    @Required
    public void setSubsSytemId(String subsSytemId)
    {
        this.subsSytemId = subsSytemId;
    }
}

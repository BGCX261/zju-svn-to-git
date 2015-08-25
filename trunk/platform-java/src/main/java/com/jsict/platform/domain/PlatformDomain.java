/**
 * PlatformDomain.java 2008-11-17 下午03:14:15 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.domain;

/**
 * 注意：该Domain类没有相对应的实体类!TODO
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class PlatformDomain
{
    String name;

    String id;

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param name
     *            The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String id.
     */
    public String getId()
    {
        return id;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param id
     *            The id to set.
     */
    public void setId(String id)
    {
        this.id = id;
    }
}

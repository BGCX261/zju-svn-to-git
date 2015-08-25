/**
 * PermissionsKeyHelper.java 2008-12-15 上午09:18:47 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class PermissionsKeyHelper
{
    private String permissionKeyClass;

    private String roleKeyClass;

    private static Map<String, String> permissionsMap = new HashMap<String, String>();

    private static Map<String, String> roleKeyMap = new HashMap<String, String>();

    public static String getPermissionCode(String varName)
    {
        return permissionsMap.get(varName);
    }

    public static String getRoleCode(String varName)
    {
        return roleKeyMap.get(varName);
    }

    public void init() throws IllegalArgumentException, ClassNotFoundException,
        IllegalAccessException
    {
        loadPermissionKeyClassContent();
        loadRoleKeyClassContent();
    }

    public void loadPermissionKeyClassContent() throws ClassNotFoundException,
        IllegalArgumentException, IllegalAccessException
    {
        Class clazz = Class.forName(permissionKeyClass);
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields)
        {
            permissionsMap.put(field.getName(), field.get(null).toString());
        }
    }

    public void loadRoleKeyClassContent() throws ClassNotFoundException,
        IllegalArgumentException, IllegalAccessException
    {
        Class clazz = Class.forName(roleKeyClass);
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields)
        {
            roleKeyMap.put(field.getName(), field.get(null).toString());
        }
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param permissionKeyClass
     *            The permissionKeyClass to set.
     */
    public void setPermissionKeyClass(String permissionKeyClass)
    {
        this.permissionKeyClass = permissionKeyClass;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String permissionKeyClass.
     */
    public String getPermissionKeyClass()
    {
        return permissionKeyClass;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param roleKeyClass
     *            The roleKeyClass to set.
     */
    public void setRoleKeyClass(String roleKeyClass)
    {
        this.roleKeyClass = roleKeyClass;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String roleKeyClass.
     */
    public String getRoleKeyClass()
    {
        return roleKeyClass;
    }

}

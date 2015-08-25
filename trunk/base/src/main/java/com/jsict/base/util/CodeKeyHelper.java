/**
 * CodeKeyHelper.java Oct 10, 2008 12:41:56 PM lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.base.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

/**
 * <p>
 * Description: This class provides mappings between variable name and variable
 * value of CodeKey Class <br/> for example INCEPT_MODE_TODOOR -->"010001" The
 * spring class is configured in springUtil.xml
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class CodeKeyHelper
{
    private String[] codeKeyClasses;

    private static Map<String, String> map = new HashMap<String, String>();

    private static int _priority = -2;

    private int priority = -1;

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param codeKeyClass
     *            The codeKeyClass to set.
     */
    @Required
    public void setCodeKeyClasses(String[] codeKeyClasses)
    {
        this.codeKeyClasses = codeKeyClasses;
    }

    public void init() throws ClassNotFoundException, IllegalArgumentException,
        IllegalAccessException
    {
        boolean load = priority > _priority;
        boolean clearMap = priority > _priority;
        _priority = priority;
        if(clearMap)
        {
            map.clear();
        }
        if(load)
        {
            for (String codeKeyClass : codeKeyClasses)
            {
                Class clazz = Class.forName(codeKeyClass);
                Field[] declaredFields = clazz.getDeclaredFields();
                for (Field field : declaredFields)
                {
                    map.put(field.getName(), field.get(null).toString());
                }
            }
        }
    }

    /**
     * provides mappings between variable name and variable value of CodeKey
     * Class <br/> for example INCEPT_MODE_TODOOR -->"010001" The spring class
     * is configured in springUtil.xml
     * 
     * @param keyVar
     *            the key var
     * 
     * @return the key value
     */
    public static String getKeyValue(String keyVar)
    {
        return map.get(keyVar);
    }

    public static List<String> getKeys()
    {
        return new ArrayList<String>(map.keySet());
    }

    public static List<String> getKeyValues()
    {
        return new ArrayList<String>(map.values());
    }
}

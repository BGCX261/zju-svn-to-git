package com.jsict.base.context;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author qipf
 *
 */
public class ThreadLocalContext
{

    private static ThreadLocal context = new ThreadLocal();

    protected static Map currentContext()
    {
        Map info = (Map) context.get();
        if (info == null)
        {
            info = new HashMap();
            context.set(info);
        }
        return info;
    }

    protected static void setAttribute(Object key, Object val)
    {
        currentContext().put(key, val);
    }

    protected static Object getAttribute(Object key)
    {
        return currentContext().get(key);
    }

    protected static void removeAttribute(Object key)
    {
        currentContext().remove(key);
    }
}
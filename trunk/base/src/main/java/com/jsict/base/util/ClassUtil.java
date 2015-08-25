package com.jsict.base.util;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ClassUtil
{
    private static final Set<Class> _simpleTypes;
    static
    {
        _simpleTypes = new HashSet<Class>();
        _simpleTypes.add(String.class);
        _simpleTypes.add(Number.class);
        _simpleTypes.add(int.class);
        _simpleTypes.add(short.class);
        _simpleTypes.add(long.class);
        _simpleTypes.add(Date.class);

    }

    @SuppressWarnings("unchecked")
    public static Class getFirstParameterizedType(Object target)
    {
        return (Class) ((ParameterizedType) target.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public static boolean isSimpleType(Class clazz)
    {
        for (Class simpleType : _simpleTypes)
        {
            if(simpleType.isAssignableFrom(clazz))
            {
                return true;
            }
        }
        return false;
    }
}

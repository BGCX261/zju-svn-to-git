package com.jsict.base.util;

import org.hibernate.proxy.HibernateProxy;

public class HibernateUtil
{
    @SuppressWarnings("unchecked")
    public static <T> T getImplementation(T proxyObj)
    {
        if(proxyObj instanceof HibernateProxy)
        {
            HibernateProxy proxy = (HibernateProxy) proxyObj;
            return (T) proxy.getHibernateLazyInitializer().getImplementation();
        }
        else
        {
            return proxyObj;
        }
    }
}

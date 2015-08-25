package com.jsict.base.util;

import net.sf.dozer.util.mapping.DozerBeanMapper;

import org.springframework.beans.factory.annotation.Required;

public class MapperUtil
{
    private DozerBeanMapper dozerMapper;

    private static final MapperUtil util = new MapperUtil();

    private MapperUtil()
    {
    }

    public static MapperUtil getInstance()
    {
        return util;
    }

    public <E> E map(Object fromObj, E toObj)
    {
        assert fromObj != null && toObj != null;
        dozerMapper.map(fromObj, toObj);
        return toObj;
    }

    @SuppressWarnings("unchecked")
    public <E> E map(Object fromObj, Class<E> toClass)
    {
        assert fromObj != null && toClass != null;
        return (E) dozerMapper.map(fromObj, toClass);
    }

    public <E> E map(Object fromObj, E toObj, String mappingId)
    {
        assert fromObj != null && toObj != null;
        dozerMapper.map(fromObj, toObj, mappingId);
        return toObj;
    }

    @SuppressWarnings("unchecked")
    public <E> E map(Object fromObj, Class<E> toClass, String mappingId)
    {
        assert fromObj != null && toClass != null;
        return (E) dozerMapper.map(fromObj, toClass, mappingId);
    }

    @Required
    public void setDozerMapper(DozerBeanMapper dozerMapper)
    {
        this.dozerMapper = dozerMapper;
    }

}

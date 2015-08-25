package com.jsict.base.util;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class AutoIncrementList<T> extends ArrayList<T>
{
    private Class<T> clazz;

    @SuppressWarnings("unchecked")
    public AutoIncrementList(Class<T> clazz)
    {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index)
    {
        try
        {
            while (index >= size())
            {
                add(clazz.newInstance());
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return super.get(index);
    }
}

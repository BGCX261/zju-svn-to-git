package com.jsict.base.dao;

import java.io.Serializable;

public interface IBaseDao<E>
{
    public E save(E o);

    public void delete(E o);

    public E get(Serializable id);

    public PagedList<E> getPagedList(EntityFilter tf, Integer pageNo,
            Integer pageSize);

    public PagedList<E> getPagedList(Integer pageNo, Integer pageSize);

    public PagedList<E> getAllList();

    public PagedList<E> getFilteredList(EntityFilter tf);
}
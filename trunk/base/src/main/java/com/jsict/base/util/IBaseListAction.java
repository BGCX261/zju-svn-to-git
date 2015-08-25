package com.jsict.base.util;

import com.jsict.base.dao.PagedList;

/**
 * The Interface IBaseListAction.
 */
public interface IBaseListAction<E>
{

    /**
     * Gets the result list.
     * 
     * @return the result list
     */
    public abstract PagedList<E> getResultList();

    /**
     * Gets the total count.
     * 
     * @return total record count in the entire database table
     */
    public abstract int getTotalCount();

    /**
     * Gets the page size.
     * 
     * @return the page size
     */
    public abstract Integer getPageSize();

    /**
     * Sets the page size.
     * 
     * @param pageSize
     *            the new page size
     */
    public abstract void setPageSize(Integer pageSize);

    /**
     * Gets the page no.
     * 
     * @return the page no
     */
    public abstract Integer getPageNo();

    /**
     * Sets the page no.
     * 
     * @param pageNo
     *            the new page no
     */
    public abstract void setPageNo(Integer pageNo);

    /**
     * Gets the total page number.
     * 
     * @return the total page number
     */
    public abstract Integer getTotalPageNumber();

    /**
     * Gets the first record pos.
     * 
     * @return the first record pos
     */
    public abstract Integer getFirstRecordPos();

}
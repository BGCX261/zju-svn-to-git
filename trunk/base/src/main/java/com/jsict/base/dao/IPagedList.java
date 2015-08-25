package com.jsict.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public interface IPagedList<E> extends Serializable
{

    /**
     * Adds the.
     * 
     * @param o
     *            the o
     * @return true, if adds the
     * @see java.util.List#add(java.lang.Object)
     */
    public abstract boolean add(E o);

    /**
     * Adds the.
     * 
     * @param index
     *            the index
     * @param element
     *            the element
     * @see java.util.List#add(int, java.lang.Object)
     */
    public abstract void add(int index, E element);

    /**
     * Adds the all.
     * 
     * @param c
     *            the c
     * @return true, if adds the all
     * @see java.util.List#addAll(java.util.Collection)
     */
    public abstract boolean addAll(Collection<? extends E> c);

    /**
     * Adds the all.
     * 
     * @param index
     *            the index
     * @param c
     *            the c
     * @return true, if adds the all
     * @see java.util.List#addAll(int, java.util.Collection)
     */
    public abstract boolean addAll(int index, Collection<? extends E> c);

    /**
     * Clear.
     * 
     * @see java.util.List#clear()
     */
    public abstract void clear();

    /**
     * Contains.
     * 
     * @param o
     *            the o
     * @return true, if contains
     * @see java.util.List#contains(java.lang.Object)
     */
    public abstract boolean contains(Object o);

    /**
     * Contains all.
     * 
     * @param c
     *            the c
     * @return true, if contains all
     * @see java.util.List#containsAll(java.util.Collection)
     */
    public abstract boolean containsAll(Collection<?> c);

    /**
     * Gets the.
     * 
     * @param index
     *            the index
     * @return the E
     * @see java.util.List#get(int)
     */
    public abstract E get(int index);

    /**
     * Index of.
     * 
     * @param o
     *            the o
     * @return the int
     * @see java.util.List#indexOf(java.lang.Object)
     */
    public abstract int indexOf(Object o);

    /**
     * Checks if is empty.
     * 
     * @return true, if checks if is empty
     * @see java.util.List#isEmpty()
     */
    public abstract boolean isEmpty();

    /**
     * Iterator.
     * 
     * @return the iterator< e>
     * @see java.util.List#iterator()
     */
    public abstract Iterator<E> iterator();

    /**
     * Last index of.
     * 
     * @param o
     *            the o
     * @return the int
     * @see java.util.List#lastIndexOf(java.lang.Object)
     */
    public abstract int lastIndexOf(Object o);

    /**
     * List iterator.
     * 
     * @return the list iterator< e>
     * @see java.util.List#listIterator()
     */
    public abstract ListIterator<E> listIterator();

    /**
     * List iterator.
     * 
     * @param index
     *            the index
     * @return the list iterator< e>
     * @see java.util.List#listIterator(int)
     */
    public abstract ListIterator<E> listIterator(int index);

    /**
     * Removes the.
     * 
     * @param index
     *            the index
     * @return the E
     * @see java.util.List#remove(int)
     */
    public abstract E remove(int index);

    /**
     * Removes the.
     * 
     * @param o
     *            the o
     * @return true, if removes the
     * @see java.util.List#remove(java.lang.Object)
     */
    public abstract boolean remove(Object o);

    /**
     * Removes the all.
     * 
     * @param c
     *            the c
     * @return true, if removes the all
     * @see java.util.List#removeAll(java.util.Collection)
     */
    public abstract boolean removeAll(Collection<?> c);

    /**
     * Retain all.
     * 
     * @param c
     *            the c
     * @return true, if retain all
     * @see java.util.List#retainAll(java.util.Collection)
     */
    public abstract boolean retainAll(Collection<?> c);

    /**
     * Sets the.
     * 
     * @param index
     *            the index
     * @param element
     *            the element
     * @return the E
     * @see java.util.List#set(int, java.lang.Object)
     */
    public abstract E set(int index, E element);

    /**
     * Size.
     * 
     * @return the int
     * @see java.util.List#size()
     */
    public abstract int size();

    /**
     * Sub list.
     * 
     * @param fromIndex
     *            the from index
     * @param toIndex
     *            the to index
     * @return the list< e>
     * @see java.util.List#subList(int, int)
     */
    public abstract List<E> subList(int fromIndex, int toIndex);

    /**
     * To array.
     * 
     * @return the object[]
     * @see java.util.List#toArray()
     */
    public abstract Object[] toArray();

    /**
     * To array.
     * 
     * @param a
     *            the a
     * @return the t[]
     * @see java.util.List#toArray(T[])
     */
    public abstract <T> T[] toArray(T[] a);

    /**
     * Gets the count.
     * 
     * @return the count
     */
    public abstract int getCount();

    /**
     * Gets the first row no.
     * 
     * @return the first row no
     */
    public abstract int getFirstResult();

    /**
     * Gets the last row no.
     * 
     * @return the last row no
     */
    public abstract int getLastResult();

    /**
     * Gets the list.
     * 
     * @return the list
     */
    public abstract List<E> getWrappedList();

    /**
     * Sets the list.
     * 
     * @param list
     *            the new list
     */
    public abstract void setWrappedList(java.util.List<E> list);

    /**
     * @return the pageNo
     */
    public abstract int getPageNo();

    /**
     * @param pageNo
     *            the pageNo to set
     */
    public abstract void setPageNo(int pageNo);

    /**
     * @return the pageSize
     */
    public abstract int getPageSize();

    /**
     * @param pageSize
     *            the pageSize to set
     */
    public abstract void setPageSize(int pageSize);

    /**
     * @return the totalPages
     */
    public abstract int getTotalPages();

    /**
     * @param totalPages
     *            the totalPages to set
     */
    public abstract void setTotalPages(int totalPages);

    /**
     * @return the totalCount
     */
    public abstract int getTotalCount();

    /**
     * @param totalCount
     *            the totalCount to set
     */
    public abstract void setTotalCount(int totalCount);
}
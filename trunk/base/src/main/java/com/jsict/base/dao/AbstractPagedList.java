package com.jsict.base.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class AbstractPagedList<E> implements IPagedList<E>
{
    /** The page. */
    protected int pageNo; // current page

    /** The page size. */
    protected int pageSize; // pageSize

    /** The total pages. */
    protected int totalPages; // total pages

    /** The total rows count. */
    protected int totalCount; // total rows count

    /** The wrapped list. */
    protected ArrayList<E> wrappedList = new ArrayList<E>();

    /**
     * Adds the.
     * 
     * @param o
     *            the o
     * @return true, if adds the
     * @see java.util.List#add(java.lang.Object)
     */
    public boolean add(E o)
    {
        return wrappedList.add(o);
    }

    /**
     * Adds the.
     * 
     * @param index
     *            the index
     * @param element
     *            the element
     * @see java.util.List#add(int, java.lang.Object)
     */
    public void add(int index, E element)
    {
        wrappedList.add(index, element);
    }

    /**
     * Adds the all.
     * 
     * @param c
     *            the c
     * @return true, if adds the all
     * @see java.util.List#addAll(java.util.Collection)
     */
    public boolean addAll(Collection<? extends E> c)
    {
        assert c != null;
        return wrappedList.addAll(c);
    }

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
    public boolean addAll(int index, Collection<? extends E> c)
    {
        assert c != null;
        return wrappedList.addAll(index, c);
    }

    /**
     * Clear.
     * 
     * @see java.util.List#clear()
     */
    public void clear()
    {
        wrappedList.clear();
    }

    /**
     * Contains.
     * 
     * @param o
     *            the o
     * @return true, if contains
     * @see java.util.List#contains(java.lang.Object)
     */
    public boolean contains(Object o)
    {
        return wrappedList.contains(o);
    }

    /**
     * Contains all.
     * 
     * @param c
     *            the c
     * @return true, if contains all
     * @see java.util.List#containsAll(java.util.Collection)
     */
    public boolean containsAll(Collection<?> c)
    {
        return wrappedList.containsAll(c);
    }

    /**
     * Gets the.
     * 
     * @param index
     *            the index
     * @return the E
     * @see java.util.List#get(int)
     */
    public E get(int index)
    {
        return wrappedList.get(index);
    }

    /**
     * Index of.
     * 
     * @param o
     *            the o
     * @return the int
     * @see java.util.List#indexOf(java.lang.Object)
     */
    public int indexOf(Object o)
    {
        return wrappedList.indexOf(o);
    }

    /**
     * Checks if is empty.
     * 
     * @return true, if checks if is empty
     * @see java.util.List#isEmpty()
     */
    public boolean isEmpty()
    {
        return wrappedList.isEmpty();
    }

    /**
     * Iterator.
     * 
     * @return the iterator< e>
     * @see java.util.List#iterator()
     */
    public Iterator<E> iterator()
    {
        return wrappedList.iterator();
    }

    /**
     * Last index of.
     * 
     * @param o
     *            the o
     * @return the int
     * @see java.util.List#lastIndexOf(java.lang.Object)
     */
    public int lastIndexOf(Object o)
    {
        return wrappedList.lastIndexOf(o);
    }

    /**
     * List iterator.
     * 
     * @return the list iterator< e>
     * @see java.util.List#listIterator()
     */
    public ListIterator<E> listIterator()
    {
        return wrappedList.listIterator();
    }

    /**
     * List iterator.
     * 
     * @param index
     *            the index
     * @return the list iterator< e>
     * @see java.util.List#listIterator(int)
     */
    public ListIterator<E> listIterator(int index)
    {
        return wrappedList.listIterator(index);
    }

    /**
     * Removes the.
     * 
     * @param index
     *            the index
     * @return the E
     * @see java.util.List#remove(int)
     */
    public E remove(int index)
    {
        return wrappedList.remove(index);
    }

    /**
     * Removes the.
     * 
     * @param o
     *            the o
     * @return true, if removes the
     * @see java.util.List#remove(java.lang.Object)
     */
    public boolean remove(Object o)
    {
        return wrappedList.remove(o);
    }

    /**
     * Removes the all.
     * 
     * @param c
     *            the c
     * @return true, if removes the all
     * @see java.util.List#removeAll(java.util.Collection)
     */
    public boolean removeAll(Collection<?> c)
    {
        return wrappedList.removeAll(c);
    }

    /**
     * Retain all.
     * 
     * @param c
     *            the c
     * @return true, if retain all
     * @see java.util.List#retainAll(java.util.Collection)
     */
    public boolean retainAll(Collection<?> c)
    {
        return wrappedList.retainAll(c);
    }

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
    public E set(int index, E element)
    {
        return wrappedList.set(index, element);
    }

    /**
     * Size.
     * 
     * @return the int
     * @see java.util.List#size()
     */
    public int size()
    {
        return wrappedList.size();
    }

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
    public List<E> subList(int fromIndex, int toIndex)
    {
        return wrappedList.subList(fromIndex, toIndex);
    }

    /**
     * To array.
     * 
     * @return the object[]
     * @see java.util.List#toArray()
     */
    public Object[] toArray()
    {
        return wrappedList.toArray();
    }

    /**
     * To array.
     * 
     * @param a
     *            the a
     * @return the t[]
     * @see java.util.List#toArray(T[])
     */
    public <T> T[] toArray(T[] a)
    {
        return wrappedList.toArray(a);
    }

    /**
     * Gets the count.
     * 
     * @return the count
     */
    public int getCount()
    {
        return wrappedList.size();
    }

    /**
     * Gets the first row no.
     * 
     * @return the first row no
     */
    public int getFirstResult()
    {
        return (getTotalCount() == 0) ? 0 : ((pageNo - 1) * pageSize) + 1;
    }

    /**
     * Gets the last row no.
     * 
     * @return the last row no
     */
    public int getLastResult()
    {
        return (pageNo == totalPages) ? totalCount : ((totalCount == 0) ? 0
                : pageNo * pageSize);
    }

    /**
     * Gets the list.
     * 
     * @return the list
     */
    public List<E> getWrappedList()
    {
        return wrappedList;
    }

    /**
     * Sets the list.
     * 
     * @param list
     *            the new list
     */
    public void setWrappedList(java.util.List<E> list)
    {
        if((list == null) || (list.size() == 0))
        {
            return;
        }
        this.wrappedList.clear();
        this.wrappedList.addAll(list);
    }

    /**
     * @return the pageNo
     */
    public int getPageNo()
    {
        return pageNo;
    }

    /**
     * @param pageNo
     *            the pageNo to set
     */
    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize()
    {
        return pageSize;
    }

    /**
     * @param pageSize
     *            the pageSize to set
     */
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    /**
     * @return the totalPages
     */
    public int getTotalPages()
    {
        return totalPages;
    }

    /**
     * @param totalPages
     *            the totalPages to set
     */
    public void setTotalPages(int totalPages)
    {
        this.totalPages = totalPages;
    }

    /**
     * @return the totalCount
     */
    public int getTotalCount()
    {
        return totalCount;
    }

    /**
     * @param totalCount
     *            the totalCount to set
     */
    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }
}

package com.jsict.base.form;

import java.util.List;

/**
 * 
 * @author qipf
 * 
 */
public abstract class BaseListForm extends BaseForm
{

    private List resultList = null;

    private int firstRecordNo = 0;

    private int lastRecordNo = 0;

    private int currentPageNo = 0;

    private int maxPageNo = 0;

    private int recordsPerPage = 0;

    private int totalRecords = 0;

    public List getResultList()
    {
        return resultList;
    }

    public void setResultList(List resultList)
    {
        this.resultList = resultList;
    }

    public int getFirstRecordNo()
    {
        return firstRecordNo;
    }

    public void setFirstRecordNo(int firstRecordNo)
    {
        this.firstRecordNo = firstRecordNo;
    }

    public int getLastRecordNo()
    {
        return lastRecordNo;
    }

    public void setLastRecordNo(int lastRecordNo)
    {
        this.lastRecordNo = lastRecordNo;
    }

    public int getCurrentPageNo()
    {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo)
    {
        this.currentPageNo = currentPageNo;
    }

    public int getMaxPageNo()
    {
        return maxPageNo;
    }

    public void setMaxPageNo(int maxPageNo)
    {
        this.maxPageNo = maxPageNo;
    }

    public int getRecordsPerPage()
    {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage)
    {
        this.recordsPerPage = recordsPerPage;
    }

    public int getTotalRecords()
    {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords)
    {
        this.totalRecords = totalRecords;
    }

}

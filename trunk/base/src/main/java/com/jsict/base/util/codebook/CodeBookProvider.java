/**
 * CodeBookProvider.java 2008-9-24 下午03:40:16 Liu GuanQun(liugq@jsict.com) 版权所有
 * (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.base.util.codebook;

import java.util.ArrayList;
import java.util.List;

import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.IBaseDao;
import com.jsict.base.dao.PagedList;

public class CodeBookProvider implements ICodeBookProvider
{
    List codeBookDaos;

    /**
     * <p>
     * Description: The getFilteredList
     * </p>
     * 
     * @param tf
     * @return
     * @author: Liu GuanQun(liugq@jsict.com)
     * @update: 2008-9-24-下午03:40:16 [变更人姓名] [变更内容]
     */

    public List<CodeBookVO> getFilteredList(EntityFilter tf)
    {
        List<CodeBookVO> allList = new ArrayList<CodeBookVO>();
        for (Object dao : codeBookDaos)
        {
            IBaseDao codeBookDao = (IBaseDao) dao;
            allList.addAll(getFilteredListForOneDao(tf, codeBookDao));
        }
        return allList;
    }

    private List<CodeBookVO> getFilteredListForOneDao(EntityFilter tf,
            IBaseDao codeBookDao)
    {
        
        PagedList<ICodeBook> list = codeBookDao.getFilteredList(tf);
        List<CodeBookVO> codeBookVOList = new ArrayList<CodeBookVO>();

        for (ICodeBook codeBook : list)
        {
            codeBookVOList.add(new CodeBookVO(codeBook.getCodeKey(), codeBook
                    .getCodeType(), codeBook.getCodeValue(), codeBook
                    .getCodeStatus(), codeBook.getCodeDesc()));

        }
        return codeBookVOList;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param codeBookDao
     *            The codeBookDao to set.
     */
    public void setCodeBookDaos(List codeBookDaos)
    {
        this.codeBookDaos = codeBookDaos;
    }

}

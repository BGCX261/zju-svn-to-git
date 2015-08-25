/**
 * ListUtil.java 2008-9-25 下午03:27:35 Liu GuanQun(liugq@jsict.com) 版权所有 (c)
 * 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.base.util;

import java.util.List;

import com.jsict.base.dao.PagedList;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public final class ListUtil
{
    /**
     * <p>
     * Copy paged information from one PagedList to another <br/>including
     * <li>PageNo</li>
     * <li>pageSize</li>
     * <li>totalCount</li>
     * <li>totalPages</li>
     * </p>
     * 
     * @param dest
     * @param src
     * @author: Liu GuanQun(liugq@jsict.com)
     * @update: 2008-9-25-下午03:29:47 [变更人姓名] [变更内容]
     */
    public static void clonePagedInfo(List dest, List src)
    {
        if(dest instanceof PagedList && src instanceof PagedList)
        {
            ((PagedList) dest).setPageNo(((PagedList) src).getPageNo());
            ((PagedList) dest).setPageSize(((PagedList) src).getPageSize());
            ((PagedList) dest).setTotalCount(((PagedList) src).getTotalCount());
            ((PagedList) dest).setTotalPages(((PagedList) src).getTotalPages());
        }
    }
}

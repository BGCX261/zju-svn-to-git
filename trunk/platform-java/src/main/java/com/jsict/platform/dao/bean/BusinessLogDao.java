/**
 * BusinessLogDao.java 2008-11-10 下午03:12:10 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dao.bean;

import java.util.List;

import com.jsict.base.dao.BaseDao;
import com.jsict.base.dao.EntityFilter;
import com.jsict.platform.dao.IBusinessLogDao;
import com.jsict.platform.entity.BusinessLog;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class BusinessLogDao extends BaseDao<BusinessLog> implements
        IBusinessLogDao
{
    private final static String GET_BUSINESSLOGLIST_WITHUSER = "select log,userAccount from BusinessLog log,UserAccount userAccount "
            + "where log.operatorId=userAccount.id";

    public List<Object[]> getBusinessLogListWithUser(EntityFilter entityFilter,
            Integer pageNo, Integer pageSize)
    {
        return executeQuery(GET_BUSINESSLOGLIST_WITHUSER, entityFilter, pageNo,
            pageSize);
    }
}

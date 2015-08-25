/**
 * IBusinessLogDao.java 2008-11-10 下午03:06:57 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.repository.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.BaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.platform.dao.IBusinessLogDao;
import com.jsict.platform.dao.IBusinessLogDetailDao;
import com.jsict.platform.repository.IBusinessLogRepository;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class BusinessLogRepository extends BaseRepository implements
        IBusinessLogRepository
{
    private IBusinessLogDao businessLogDao;

    private IBusinessLogDetailDao businessLogDetailDao;

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param businessLogDao
     *            The businessLogDao to set.
     */
    @Required
    public void setBusinessLogDao(IBusinessLogDao businessLogDao)
    {
        this.businessLogDao = businessLogDao;
    }

    @Required
    public void setBusinessLogDetailDao(
            IBusinessLogDetailDao businessLogDetailDao)
    {
        this.businessLogDetailDao = businessLogDetailDao;
    }

    public List<Object[]> getBusinessLogListWithUser(EntityFilter entityFilter,
            Integer pageNo, Integer pageSize)
    {
        return businessLogDao.getBusinessLogListWithUser(entityFilter, pageNo,
            pageSize);

    }
}

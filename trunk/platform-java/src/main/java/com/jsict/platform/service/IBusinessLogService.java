/**
 * IBusinessLogService.java 2008-11-10 下午03:40:27 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.service;

import java.util.List;

import com.jsict.base.IBaseService;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.platform.domain.BusinessLogDomain;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public interface IBusinessLogService extends IBaseService
{

    List<BusinessLogDomain> getBusinessLogList(EntityFilter entityFilter,
            Integer pageNo, Integer pageSize, boolean withDetail)
        throws ApplicationException, SystemException;

}

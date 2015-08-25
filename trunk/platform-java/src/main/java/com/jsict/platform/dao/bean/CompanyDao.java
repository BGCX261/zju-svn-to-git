/**
 * CompanyDao.java 2008-11-10 下午03:15:21 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dao.bean;

import java.util.List;

import com.jsict.base.dao.BaseDao;
import com.jsict.platform.dao.ICompanyDao;
import com.jsict.platform.entity.Company;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class CompanyDao extends BaseDao<Company> implements ICompanyDao
{
    public final static String GET_COMPANY_BY_STATUS = "from Company where status in (?)";

    public List<Company> getCompanyByStatus(String... status)
    {
        return executeQuery(GET_COMPANY_BY_STATUS, (status));
    }
}

/**
 * ICompanyService.java 2008-11-10 下午03:39:11 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.service;

import java.io.Serializable;
import java.util.List;

import com.jsict.base.IBaseService;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.platform.domain.CompanyDomain;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * .
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public interface ICompanyService extends IBaseService
{

    /**
     * Gets the company list.
     * 
     * @param entityFilter
     *            the entity filter
     * @param pageNo
     *            the page no
     * @param pageSize
     *            the page size
     * 
     * @return the company list
     * 
     * @throws ApplicationException
     *             the application exception
     * @throws SystemException
     *             the system exception
     */
    PagedList<CompanyDomain> getCompanyList(EntityFilter entityFilter,
            Integer pageNo, Integer pageSize) throws ApplicationException,
        SystemException;

    /**
     * <p>
     * Description: The Company domain.
     * </p>
     * 
     * @param companyDomain
     *            the company domain
     * 
     * @return the company domain
     * 
     * @throws SystemException
     *             the system exception
     * @throws ApplicationException
     *             the application exception
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    CompanyDomain save(CompanyDomain companyDomain) throws SystemException,
        ApplicationException;

    /**
     * <p>
     * Description: The Delete.
     * </p>
     * 
     * @param companyDomain
     *            the company domain
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    void delete(CompanyDomain companyDomain);

    /**
     * Gets the company.
     * 
     * @param companyId
     *            the company id
     * 
     * @return the company
     * 
     * @throws ApplicationException
     *             the application exception
     * @throws SystemException
     *             the system exception
     */
    CompanyDomain getCompany(Serializable companyId)
        throws ApplicationException, SystemException;

    /**
     * <p>
     * Description: The Company domain.
     * </p>
     * 
     * @param companyDomain
     *            the company domain
     * 
     * @return the company domain
     * 
     * @throws SystemException
     *             the system exception
     * @throws ApplicationException
     *             the application exception
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    CompanyDomain update(CompanyDomain companyDomain) throws SystemException,
        ApplicationException;

    /**
     * <p>
     * Description: The Disable company.
     * </p>
     * 
     * @param companyId
     *            the company id
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    void disableCompany(Serializable companyId);

    /**
     * <p>
     * Description: The Enable company.
     * </p>
     * 
     * @param companyId
     *            the company id
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    void enableCompany(Serializable companyId);

    /**
     * Gets the available company list.
     * 
     * @return the available company list
     * @throws SystemException
     * @throws ApplicationException
     */
    List<CompanyDomain> getAvailableCompanyList() throws ApplicationException,
        SystemException;

    CompanyDomain getCompanyByUserAccountId(Serializable userAccountId)
        throws ApplicationException, SystemException;
}

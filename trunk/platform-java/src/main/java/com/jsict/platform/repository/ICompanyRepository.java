/**
 * ICompanyRepository.java 2008-11-10 下午03:06:57 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.repository;

import java.io.Serializable;
import java.util.List;

import com.jsict.base.IBaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.platform.entity.Company;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>.
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public interface ICompanyRepository extends IBaseRepository
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
     */
    PagedList<Company> getCompanyList(EntityFilter entityFilter,
            Integer pageNo, Integer pageSize);

    /**
     * <p>
     * Description: The Company.
     * </p>
     * 
     * @param company
     *            the company
     * 
     * @return the company
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    Company save(Company company);

    /**
     * <p>
     * Description: The Delete.
     * </p>
     * 
     * @param company
     *            the company
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    void delete(Company company);

    /**
     * Gets the company.
     * 
     * @param companyId
     *            the company id
     * 
     * @return the company
     */
    Company getCompany(Serializable companyId);

    /**
     * <p>
     * Description: The Disable.
     * </p>
     * 
     * @param company
     *            the company
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    void disable(Company company);

    /**
     * <p>
     * Description: The Enable.
     * </p>
     * 
     * @param company
     *            the company
     * 
     * @author: Administrator
     * @update:[updatedate] [changer][change description]
     */
    void enable(Company company);
    
    /**
     * Gets the available company list.
     * 
     * @return the available company list
     */
    List<Company> getAvailableCompanyList();
}

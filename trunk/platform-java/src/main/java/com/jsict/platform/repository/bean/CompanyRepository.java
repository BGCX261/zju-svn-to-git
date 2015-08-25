/**
 * ICompanyRepository.java 2008-11-10 下午03:06:57 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.repository.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.BaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.platform.constants.CodeKey;
import com.jsict.platform.dao.ICompanyDao;
import com.jsict.platform.entity.Company;
import com.jsict.platform.repository.ICompanyRepository;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class CompanyRepository extends BaseRepository implements
        ICompanyRepository
{
    private ICompanyDao companyDao;

    public void delete(Company company)
    {
        companyDao.delete(company);

    }

    public Company getCompany(Serializable companyId)
    {
        return companyDao.get(companyId);
    }

    public PagedList<Company> getCompanyList(EntityFilter entityFilter,
            Integer pageNo, Integer pageSize)
    {
        return companyDao.getPagedList(entityFilter, pageNo, pageSize);
    }

    public Company save(Company company)
    {
        return companyDao.save(company);
    }

    public void disable(Company company)
    {
        company.setStatus(CodeKey.COMPANY_STATUS_UNAVAILABLE);
        companyDao.save(company);
    }

    public void enable(Company company)
    {
        company.setStatus(CodeKey.COMPANY_STATUS_AVAILABLE);
        companyDao.save(company);

    }

    public List<Company> getAvailableCompanyList()
    {
        return companyDao.getCompanyByStatus(CodeKey.COMPANY_STATUS_AVAILABLE);
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param companyDao
     *            The companyDao to set.
     */
    @Required
    public void setCompanyDao(ICompanyDao companyDao)
    {
        this.companyDao = companyDao;
    }
}

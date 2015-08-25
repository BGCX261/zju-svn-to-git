/**
 * CompanyService.java 2008-11-10 下午03:39:11 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.service.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.jsict.base.BaseService;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.ConvertUtil;
import com.jsict.base.util.ListUtil;
import com.jsict.platform.domain.CompanyDomain;
import com.jsict.platform.entity.Company;
import com.jsict.platform.entity.UserAccount;
import com.jsict.platform.repository.ICompanyRepository;
import com.jsict.platform.repository.IUserAccountRepository;
import com.jsict.platform.service.ICompanyService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
@Transactional
public class CompanyService extends BaseService implements ICompanyService
{
    private ICompanyRepository companyRepository;

    private IUserAccountRepository userAccountRepository;

    public void delete(CompanyDomain companyDomain)
    {
        Long companyId = Long.parseLong(companyDomain.getId());

        Company company = companyRepository.getCompany(companyId);

        companyRepository.delete(company);
    }

    public CompanyDomain getCompanyByUserAccountId(Serializable userAccountId)
        throws ApplicationException, SystemException
    {
        UserAccount userAccount = userAccountRepository
                .getUserAccount(userAccountId);
        CompanyDomain companyDomain = new CompanyDomain();
        ConvertUtil.entity2domain(userAccount.getCompany(), companyDomain);
        return companyDomain;
    }

    public CompanyDomain getCompany(Serializable companyId)
        throws ApplicationException, SystemException
    {
        CompanyDomain companyDomain = new CompanyDomain();
        Company company = companyRepository.getCompany(companyId);

        ConvertUtil.entity2domain(company, companyDomain);
        return companyDomain;
    }

    public PagedList<CompanyDomain> getCompanyList(EntityFilter entityFilter,
            Integer pageNo, Integer pageSize) throws ApplicationException,
        SystemException
    {
        PagedList<Company> companyList = companyRepository.getCompanyList(
            entityFilter, pageNo, pageSize);
        PagedList<CompanyDomain> domainList = new PagedList<CompanyDomain>();

        CompanyDomain companyDomain = null;
        for (Company company : companyList)
        {
            companyDomain = new CompanyDomain();

            ConvertUtil.entity2domain(company, companyDomain);

            domainList.add(companyDomain);
        }

        ListUtil.clonePagedInfo(domainList, companyList);
        return domainList;
    }

    public CompanyDomain save(CompanyDomain companyDomain)
        throws SystemException, ApplicationException
    {
        return convertCompanyToDomain(convertCompanyFromDomain(companyDomain,
            new Company()));
    }

    public CompanyDomain update(CompanyDomain companyDomain)
        throws SystemException, ApplicationException
    {
        return convertCompanyToDomain(convertCompanyFromDomain(companyDomain,
            companyRepository.getCompany(Long.parseLong(companyDomain.getId()))));
    }

    private CompanyDomain convertCompanyToDomain(Company company)
        throws ApplicationException, SystemException
    {
        CompanyDomain companyDomain = new CompanyDomain();

        ConvertUtil.entity2domain(company, companyDomain);

        return companyDomain;
    }

    private Company convertCompanyFromDomain(CompanyDomain companyDomain,
            Company company) throws SystemException, ApplicationException
    {
        ConvertUtil.domain2entity(companyDomain, company);

        company = companyRepository.save(company);

        return company;

    }

    public void disableCompany(Serializable companyId)
    {
        companyRepository.disable(companyRepository.getCompany(Long
                .parseLong(companyId.toString())));

    }

    public void enableCompany(Serializable companyId)
    {
        companyRepository.enable(companyRepository.getCompany(Long
                .parseLong(companyId.toString())));

    }

    public List<CompanyDomain> getAvailableCompanyList()
        throws ApplicationException, SystemException
    {
        List<Company> companyList = companyRepository.getAvailableCompanyList();
        List<CompanyDomain> domainList = new ArrayList<CompanyDomain>();

        CompanyDomain companyDomain = null;
        for (Company company : companyList)
        {
            companyDomain = new CompanyDomain();

            ConvertUtil.entity2domain(company, companyDomain);

            domainList.add(companyDomain);
        }

        return domainList;
    }

    @Required
    public void setUserAccountRepository(
            IUserAccountRepository userAccountRepository)
    {
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param companyRepository
     *            The companyRepository to set.
     */
    @Required
    public void setCompanyRepository(ICompanyRepository companyRepository)
    {
        this.companyRepository = companyRepository;
    }
}

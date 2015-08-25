/**
 * CompanyForm.java 2008-11-12 下午02:05:51 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.form;

import com.jsict.base.form.BaseForm;
import com.jsict.platform.domain.CompanyDomain;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class CompanyViewForm extends BaseForm
{
    private CompanyDomain companyDomain;

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return CompanyDomain companyDomain.
     */
    public CompanyDomain getCompanyDomain()
    {
        return companyDomain;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param companyDomain
     *            The companyDomain to set.
     */
    public void setCompanyDomain(CompanyDomain companyDomain)
    {
        this.companyDomain = companyDomain;
    }

}

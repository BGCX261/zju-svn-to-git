package com.jsict.platform.form;

import java.util.List;

import com.jsict.base.form.BaseForm;
import com.jsict.base.util.tags.SelectItem;
import com.jsict.platform.domain.UserAccountDomain;

public class UserAccountViewForm extends BaseForm
{

    private UserAccountDomain userAccountDomain;

    private List<SelectItem> companyOptions;

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return UserAccountDomain userAccountDomain.
     */
    public UserAccountDomain getUserAccountDomain()
    {
        return userAccountDomain;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param userAccountDomain
     *            The userAccountDomain to set.
     */
    public void setUserAccountDomain(UserAccountDomain userAccountDomain)
    {
        this.userAccountDomain = userAccountDomain;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return List<CompanyDomain> companyOptions.
     */
    public List<SelectItem> getCompanyOptions()
    {
        return companyOptions;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param companyOptions
     *            The companyOptions to set.
     */
    public void setCompanyOptions(List<SelectItem> companyOptions)
    {
        this.companyOptions = companyOptions;
    }

}

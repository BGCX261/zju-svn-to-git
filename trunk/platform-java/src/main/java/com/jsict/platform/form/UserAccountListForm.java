package com.jsict.platform.form;

import java.util.ArrayList;
import java.util.List;

import com.jsict.base.form.BaseListForm;
import com.jsict.base.util.tags.SelectItem;
import com.jsict.platform.domain.CompanyDomain;
import com.jsict.platform.domain.RoleDomain;

public class UserAccountListForm extends BaseListForm
{
    private List<SelectItem> companyOptions;

    private List<SelectItem> roleOptions;

    private CompanyDomain fixedCompany;

    private List<RoleDomain> fixedRoles = new ArrayList<RoleDomain>();

    private String assignedRoles = "";

    /**
     * 
     */
    private static final long serialVersionUID = 7186124634762139798L;

    public void setCompanyOptions(List<SelectItem> companyOptions)
    {

        this.companyOptions = companyOptions;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return List<SelectItem> companyOptions.
     */
    public List<SelectItem> getCompanyOptions()
    {
        return companyOptions;
    }

    public List<SelectItem> getRoleOptions()
    {
        return roleOptions;
    }

    public void setRoleOptions(List<SelectItem> roleOptions)
    {
        this.roleOptions = roleOptions;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param fixedCompany
     *            The fixedCompany to set.
     */
    public void setFixedCompany(CompanyDomain fixedCompany)
    {
        this.fixedCompany = fixedCompany;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return CompanyDomain fixedCompany.
     */
    public CompanyDomain getFixedCompany()
    {
        return fixedCompany;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param fixedRoles
     *            The fixedRoles to set.
     */
    public void setFixedRoles(List<RoleDomain> fixedRoles)
    {
        this.fixedRoles = fixedRoles;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return List<RoleDomain> fixedRoles.
     */
    public List<RoleDomain> getFixedRoles()
    {
        return fixedRoles;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param assignedRoles
     *            The assignedRoles to set.
     */
    public void setAssignedRoles(String assignedRoles)
    {
        this.assignedRoles = assignedRoles;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String assignedRoles.
     */
    public String getAssignedRoles()
    {
        return assignedRoles;
    }

}

package com.jsict.platform.form;

import com.jsict.base.form.BaseForm;
import com.jsict.platform.domain.RoleDomain;

public class RoleViewForm extends BaseForm
{

    private RoleDomain roleDomain;

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return RoleDomain roleDomain.
     */
    public RoleDomain getRoleDomain()
    {
        return roleDomain;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param roleDomain
     *            The roleDomain to set.
     */
    public void setRoleDomain(RoleDomain roleDomain)
    {
        this.roleDomain = roleDomain;
    }

}

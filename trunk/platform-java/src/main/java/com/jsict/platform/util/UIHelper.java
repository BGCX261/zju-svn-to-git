package com.jsict.platform.util;

import java.util.ArrayList;
import java.util.List;

import com.jsict.base.util.tags.SelectItem;
import com.jsict.platform.domain.CompanyDomain;
import com.jsict.platform.domain.RoleDomain;

public class UIHelper
{
    public static List<SelectItem> getCompanyOptions(
            List<CompanyDomain> companies)
    {
        List<SelectItem> companyOptions = new ArrayList<SelectItem>();
        for (CompanyDomain companyDomain : companies)
        {

            companyOptions.add(new SelectItem(companyDomain.getName(),
                companyDomain.getId()));
        }
        return companyOptions;
    }
    
    public static List<SelectItem> getRoleOptions(
            List<RoleDomain> roles)
    {
        List<SelectItem> roleOptions = new ArrayList<SelectItem>();
        for (RoleDomain roleDomain : roles)
        {

        	roleOptions.add(new SelectItem(roleDomain.getName(),
            		roleDomain.getId()));
        }
        return roleOptions;
    }
}

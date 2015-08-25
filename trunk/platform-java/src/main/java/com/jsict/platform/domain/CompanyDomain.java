package com.jsict.platform.domain;

import java.util.ArrayList;
import java.util.List;

import com.jsict.base.BaseDomain;
import com.jsict.base.util.Consts;
import com.jsict.base.util.Text;

public class CompanyDomain extends BaseDomain
{
    private String name;

    private String code;

    private String description;

    private String address;

    private String phone;

    private String fax;

    private String primaryBusiness;

    private String legalPerson;

    private String createdDate;

    private String status;

    private List<UserAccountDomain> userAccountDomainList;

    public CompanyDomain()
    {
        userAccountDomainList = new ArrayList<UserAccountDomain>();
    }

    @Text(label = "公司名称")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Text(label = "公司编码")
    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    @Text(label = "描述")
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Text(label = "地址")
    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Text(label = "电话")
    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Text(label = "传真")
    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    @Text(label = "主营业务")
    public String getPrimaryBusiness()
    {
        return primaryBusiness;
    }

    public void setPrimaryBusiness(String primaryBusiness)
    {
        this.primaryBusiness = primaryBusiness;
    }

    @Text(label = "法人代表")
    public String getLegalPerson()
    {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson)
    {
        this.legalPerson = legalPerson;
    }

    @Text(label = "法人代表", format = Consts.DATE_TIME_FORMAT)
    public String getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(String createdDate)
    {
        this.createdDate = createdDate;
    }

    @Text(label = "状态")
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Text(convert = false)
    public List<UserAccountDomain> getUserAccountDomainList()
    {
        return userAccountDomainList;
    }

    public void setUserAccountDomainList(
            List<UserAccountDomain> userAccountDomainList)
    {
        this.userAccountDomainList = userAccountDomainList;
    }

}

package com.jsict.platform.domain;

import java.util.List;

import com.jsict.base.util.Consts;
import com.jsict.base.util.Text;

/**
 * BusinessLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BusinessLogDomain implements java.io.Serializable
{

    // Fields

    private String id;

    private String objectName;

    private String type;

    private String createdDate;

    private UserAccountDomain userAccount;

    private String subSystem;

    private List<BusinessLogDetailDomain> businessLogDetails;

    // Constructors

    /** default constructor */
    public BusinessLogDomain()
    {
    }

    @Text
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Text
    public String getObjectName()
    {
        return objectName;
    }

    public void setObjectName(String objectName)
    {
        this.objectName = objectName;
    }

    @Text
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Text(format = Consts.DATE_TIME_FORMAT)
    public String getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(String createdDate)
    {
        this.createdDate = createdDate;
    }

    public UserAccountDomain getUserAccount()
    {
        return userAccount;
    }

    public void setUserAccount(UserAccountDomain userAccount)
    {
        this.userAccount = userAccount;
    }

    public List<BusinessLogDetailDomain> getBusinessLogDetails()
    {
        return businessLogDetails;
    }

    public void setBusinessLogDetails(
            List<BusinessLogDetailDomain> businessLogDetails)
    {
        this.businessLogDetails = businessLogDetails;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        final BusinessLogDomain other = (BusinessLogDomain) obj;
        if(id == null)
        {
            if(other.id != null)
                return false;
        }
        else if(!id.equals(other.id))
            return false;
        return true;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param subSystem
     *            The subSystem to set.
     */
    public void setSubSystem(String subSystem)
    {
        this.subSystem = subSystem;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String subSystem.
     */
    @Text
    public String getSubSystem()
    {
        return subSystem;
    }

}
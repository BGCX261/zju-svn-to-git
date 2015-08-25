package com.jsict.platform.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BusinessLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BUSINESS_LOG")
public class BusinessLog implements java.io.Serializable
{

    // Fields

    private Long id;

    private String objectName;

    private String type;

    private Date createdDate;

    private String operatorId;

    private String subSystem;

    private Set<BusinessLogDetail> businessLogDetails = new HashSet<BusinessLogDetail>(
        0);

    // Constructors

    /** default constructor */
    public BusinessLog()
    {
    }

    /** minimal constructor */
    public BusinessLog(String objectName, String type)
    {
        this.objectName = objectName;
        this.type = type;
    }

    /** full constructor */
    public BusinessLog(String objectName, String type, Date createdDate,
            Set<BusinessLogDetail> businessLogDetails)
    {
        this.objectName = objectName;
        this.type = type;
        this.createdDate = createdDate;
        this.businessLogDetails = businessLogDetails;
    }

    // Property accessors
    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Column(name = "OBJECT_NAME", nullable = false, length = 200)
    public String getObjectName()
    {
        return this.objectName;
    }

    public void setObjectName(String objectName)
    {
        this.objectName = objectName;
    }

    @Column(name = "operatorid", nullable = true, length = 200)
    public String getOperatorId()
    {
        return this.operatorId;
    }

    public void setOperatorId(String operator)
    {
        this.operatorId = operator;
    }

    @Column(name = "TYPE", nullable = false, length = 6)
    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", length = 7)
    public Date getCreatedDate()
    {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "businessLog")
    public Set<BusinessLogDetail> getBusinessLogDetails()
    {
        return this.businessLogDetails;
    }

    public void setBusinessLogDetails(Set<BusinessLogDetail> businessLogDetails)
    {
        this.businessLogDetails = businessLogDetails;
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
    @Column(name = "SUBSYSTEM", nullable = true, length = 50)
    public String getSubSystem()
    {
        return subSystem;
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
        final BusinessLog other = (BusinessLog) obj;
        if(id == null)
        {
            if(other.id != null)
                return false;
        }
        else if(!id.equals(other.id))
            return false;
        return true;
    }

}
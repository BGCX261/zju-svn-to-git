package com.jsict.platform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * BusinessLogDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BUSINESS_LOG_DETAIL")
public class BusinessLogDetail implements java.io.Serializable
{

    // Fields

    private Long id;

    private BusinessLog businessLog;

    private String fieldName;

    private String originValue;

    private String newValue;

    private String type;

    // Constructors

    /** default constructor */
    public BusinessLogDetail()
    {
    }

    /** full constructor */
    public BusinessLogDetail(BusinessLog businessLog, String fieldName,
            String originValue, String newValue, String type)
    {
        this.businessLog = businessLog;
        this.fieldName = fieldName;
        this.originValue = originValue;
        this.newValue = newValue;
        this.type = type;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOG_ID", nullable = false)
    public BusinessLog getBusinessLog()
    {
        return this.businessLog;
    }

    public void setBusinessLog(BusinessLog businessLog)
    {
        this.businessLog = businessLog;
    }

    @Column(name = "FIELD_NAME", nullable = false, length = 50)
    public String getFieldName()
    {
        return this.fieldName;
    }

    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    @Column(name = "ORIGIN_VALUE", nullable = true, length = 200)
    public String getOriginValue()
    {
        return this.originValue;
    }

    public void setOriginValue(String originValue)
    {
        this.originValue = originValue;
    }

    @Column(name = "NEW_VALUE", nullable = true, length = 200)
    public String getNewValue()
    {
        return this.newValue;
    }

    public void setNewValue(String newValue)
    {
        this.newValue = newValue;
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
        final BusinessLogDetail other = (BusinessLogDetail) obj;
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
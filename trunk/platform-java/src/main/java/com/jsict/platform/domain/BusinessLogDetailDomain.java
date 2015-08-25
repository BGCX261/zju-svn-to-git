package com.jsict.platform.domain;

import com.jsict.base.util.Text;

/**
 * BusinessLogDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BusinessLogDetailDomain implements java.io.Serializable
{

    // Fields

    private String id;

    private BusinessLogDomain businessLog;

    private String fieldName;

    private String originValue;

    private String newValue;

    private String type;

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
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
        final BusinessLogDetailDomain other = (BusinessLogDetailDomain) obj;
        if(getId() == null)
        {
            if(other.getId() != null)
                return false;
        }
        else if(!getId().equals(other.getId()))
            return false;
        return true;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param id
     *            The id to set.
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String id.
     */
    @Text
    public String getId()
    {
        return id;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param businessLog
     *            The businessLog to set.
     */
    public void setBusinessLog(BusinessLogDomain businessLog)
    {
        this.businessLog = businessLog;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return BusinessLogDomain businessLog.
     */
    public BusinessLogDomain getBusinessLog()
    {
        return businessLog;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param fieldName
     *            The fieldName to set.
     */
    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String fieldName.
     */
    @Text
    public String getFieldName()
    {
        return fieldName;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param originValue
     *            The originValue to set.
     */
    public void setOriginValue(String originValue)
    {
        this.originValue = originValue;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String originValue.
     */
    @Text
    public String getOriginValue()
    {
        return originValue;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param newValue
     *            The newValue to set.
     */
    public void setNewValue(String newValue)
    {
        this.newValue = newValue;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String newValue.
     */
    @Text
    public String getNewValue()
    {
        return newValue;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param type
     *            The type to set.
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @return String type.
     */
    @Text
    public String getType()
    {
        return type;
    }

}
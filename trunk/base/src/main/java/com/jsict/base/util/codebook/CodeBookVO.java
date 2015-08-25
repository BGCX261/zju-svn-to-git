/**
 * CodeBookVO.java        2008-9-24 下午03:24:24
 * Liu GuanQun(liugq@jsict.com)
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.base.util.codebook;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$ 
 */
public class CodeBookVO
{
    private String codeKey;

    private String codeType;

    private String codeValue;

    private Integer codeStatus;

    private String codeDesc;

    public CodeBookVO()
    {
    }

    public CodeBookVO(String codeKey, String codeType, String codeValue,
            Integer codeStatus, String codeDesc)
    {
        super();
        this.codeKey = codeKey;
        this.codeType = codeType;
        this.codeValue = codeValue;
        this.codeStatus = codeStatus;
        this.codeDesc = codeDesc;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @return String codeKey.
     */
    public String getCodeKey()
    {
        return codeKey;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @param codeKey The codeKey to set.
     */
    public void setCodeKey(String codeKey)
    {
        this.codeKey = codeKey;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @return String codeType.
     */
    public String getCodeType()
    {
        return codeType;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @param codeType The codeType to set.
     */
    public void setCodeType(String codeType)
    {
        this.codeType = codeType;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @return String codeValue.
     */
    public String getCodeValue()
    {
        return codeValue;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @param codeValue The codeValue to set.
     */
    public void setCodeValue(String codeValue)
    {
        this.codeValue = codeValue;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @return Integer codeStatus.
     */
    public Integer getCodeStatus()
    {
        return codeStatus;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @param codeStatus The codeStatus to set.
     */
    public void setCodeStatus(Integer codeStatus)
    {
        this.codeStatus = codeStatus;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @return String codeDesc.
     */
    public String getCodeDesc()
    {
        return codeDesc;
    }

    /**
     * <p>Description:[方法功能中文描述]</p>
     * @param codeDesc The codeDesc to set.
     */
    public void setCodeDesc(String codeDesc)
    {
        this.codeDesc = codeDesc;
    }

}

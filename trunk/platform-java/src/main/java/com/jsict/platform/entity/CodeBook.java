package com.jsict.platform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jsict.base.util.codebook.ICodeBook;

/**
 * CodeBook entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CODE_BOOK")
public class CodeBook implements java.io.Serializable, ICodeBook
{

    // Fields

    private String codeKey;

    private String codeType;

    private String codeValue;

    private Integer codeStatus;

    private String codeDesc;

    // Constructors

    /** default constructor */
    public CodeBook()
    {
    }

    /** full constructor */
    public CodeBook(String codeType, String codeValue, Integer codeStatus,
            String codeDesc)
    {
        this.codeType = codeType;
        this.codeValue = codeValue;
        this.codeStatus = codeStatus;
        this.codeDesc = codeDesc;
    }

    // Property accessors
    @Id
    @GeneratedValue
    @Column(name = "CODE_KEY", unique = true, nullable = false, length = 6)
    public String getCodeKey()
    {
        return this.codeKey;
    }

    public void setCodeKey(String codeKey)
    {
        this.codeKey = codeKey;
    }

    @Column(name = "CODE_TYPE", length = 2)
    public String getCodeType()
    {
        return this.codeType;
    }

    public void setCodeType(String codeType)
    {
        this.codeType = codeType;
    }

    @Column(name = "CODE_VALUE", length = 100)
    public String getCodeValue()
    {
        return this.codeValue;
    }

    public void setCodeValue(String codeValue)
    {
        this.codeValue = codeValue;
    }

    @Column(name = "CODE_STATUS", precision = 22, scale = 0)
    public Integer getCodeStatus()
    {
        return this.codeStatus;
    }

    public void setCodeStatus(Integer codeStatus)
    {
        this.codeStatus = codeStatus;
    }

    @Column(name = "CODE_DESC", length = 4000)
    public String getCodeDesc()
    {
        return this.codeDesc;
    }

    public void setCodeDesc(String codeDesc)
    {
        this.codeDesc = codeDesc;
    }

}
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
 * UserProfile entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "USER_PROFILE")
public class UserProfile implements java.io.Serializable
{

    // Fields

    private Long id;

    private UserAccount useraccount;

    private String code;

    private String type;

    private String value;

    // Constructors

    /** default constructor */
    public UserProfile()
    {
    }

    /** full constructor */
    public UserProfile(UserAccount useraccount, String code, String type,
            String value)
    {
        this.useraccount = useraccount;
        this.code = code;
        this.type = type;
        this.value = value;
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
    @JoinColumn(name = "USER_ID", nullable = false)
    public UserAccount getUseraccount()
    {
        return this.useraccount;
    }

    public void setUseraccount(UserAccount useraccount)
    {
        this.useraccount = useraccount;
    }

    @Column(name = "CODE", nullable = false, length = 6)
    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code = code;
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

    @Column(name = "VALUE", nullable = false, length = 200)
    public String getValue()
    {
        return this.value;
    }

    public void setValue(String value)
    {
        this.value = value;
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
        final UserProfile other = (UserProfile) obj;
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
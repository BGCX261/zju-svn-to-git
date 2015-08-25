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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * Company entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COMPANY", uniqueConstraints = @UniqueConstraint(columnNames = "CODE"))
public class Company implements java.io.Serializable
{

    // Fields

    private Long id;

    private Long version;

    private String name;

    private String code;

    private String description;

    private String address;

    private String phone;

    private String fax;

    private String primaryBusiness;

    private String legalPerson;

    private Date createdDate;

    private String status;

    private Set<UserAccount> userAccounts = new HashSet<UserAccount>(0);

    // Constructors

    /** default constructor */
    public Company()
    {
    }

    /** minimal constructor */
    public Company(String name, String code, Date createdDate, String status)
    {
        this.name = name;
        this.code = code;
        this.createdDate = createdDate;
        this.status = status;
    }

    /** full constructor */
    public Company(String name, String code, String description,
            String address, String phone, String fax, String primaryBusiness,
            String legalPerson, Date createdDate, String status,
            Set<UserAccount> useraccounts)
    {
        this.name = name;
        this.code = code;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.fax = fax;
        this.primaryBusiness = primaryBusiness;
        this.legalPerson = legalPerson;
        this.createdDate = createdDate;
        this.status = status;
        this.userAccounts = useraccounts;
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

    @Version
    @Column(name = "VERSION", precision = 22, scale = 0)
    public Long getVersion()
    {
        return this.version;
    }

    public void setVersion(Long version)
    {
        this.version = version;
    }

    @Column(name = "NAME", nullable = false, length = 200)
    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Column(name = "CODE", unique = true, nullable = false, length = 20)
    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    @Column(name = "DESCRIPTION", length = 2000)
    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Column(name = "ADDRESS", length = 2000)
    public String getAddress()
    {
        return this.address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Column(name = "PHONE", length = 100)
    public String getPhone()
    {
        return this.phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Column(name = "FAX", length = 100)
    public String getFax()
    {
        return this.fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    @Column(name = "PRIMARY_BUSINESS", length = 200)
    public String getPrimaryBusiness()
    {
        return this.primaryBusiness;
    }

    public void setPrimaryBusiness(String primaryBusiness)
    {
        this.primaryBusiness = primaryBusiness;
    }

    @Column(name = "LEGAL_PERSON", length = 50)
    public String getLegalPerson()
    {
        return this.legalPerson;
    }

    public void setLegalPerson(String legalPerson)
    {
        this.legalPerson = legalPerson;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", nullable = false, length = 7)
    public Date getCreatedDate()
    {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }

    @Column(name = "STATUS", nullable = false, length = 6)
    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    public Set<UserAccount> getUserAccounts()
    {
        return this.userAccounts;
    }

    public void setUserAccounts(Set<UserAccount> useraccounts)
    {
        this.userAccounts = useraccounts;
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
        final Company other = (Company) obj;
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
package com.jsict.platform.entity;

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

/**
 * Subsystem entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SUBSYSTEM")
public class Subsystem implements java.io.Serializable
{

    // Fields    

    private String id;

    private String code;

    private String name;

    private String url;

    private String urlHttps;


    private Set<UserAccount> userAccounts = new HashSet<UserAccount>(0);

    // Constructors

    /** default constructor */
    public Subsystem()
    {
    }

    /** minimal constructor */
    public Subsystem(String code, String name)
    {
        this.code = code;
        this.name = name;
    }

    /** full constructor */
    public Subsystem(String code, String name, String url,
            Set<Permission> permissions)
    {
        this.code = code;
        this.name = name;
        this.url = url;
    }

    // Property accessors
    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false, length = 6)
    public String getId()
    {
        return this.id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Column(name = "CODE", nullable = false, length = 20)
    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code = code;
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

    @Column(name = "URL", length = 1000)
    public String getUrl()
    {
        return this.url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    @Column(name = "URLHTTPS", length = 1000)
    public String getUrlHttps()
    {
        return this.urlHttps;
    }

    public void setUrlHttps(String url)
    {
        this.urlHttps = url;
    }


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subsystem")
    public Set<UserAccount> getUserAccounts()
    {
        return this.userAccounts;
    }

    public void setUserAccounts(Set<UserAccount> userAccounts)
    {
        this.userAccounts = userAccounts;
    }

}
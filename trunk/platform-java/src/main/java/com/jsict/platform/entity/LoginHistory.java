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
 * LoginHistory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LOGIN_HISTORY")
public class LoginHistory implements java.io.Serializable
{

    // Fields    

    private Long id;

    private UserAccount userAccount;

    private Long useraccountid;

    private String ip;

    private String logindate;

    private Long wrongcount;

    private String status;

    // Constructors

    /** default constructor */
    public LoginHistory()
    {
    }

    /** minimal constructor */
    public LoginHistory(UserAccount userAccount)
    {
        this.userAccount = userAccount;
    }

    /** full constructor */
    public LoginHistory(UserAccount userAccount, Long useraccountid, String ip,
            String logindate, Long wrongcount, String status)
    {
        this.userAccount = userAccount;
        this.useraccountid = useraccountid;
        this.ip = ip;
        this.logindate = logindate;
        this.wrongcount = wrongcount;
        this.status = status;
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
    @JoinColumn(name = "ID", unique = true, nullable = false, insertable = false, updatable = false)
    public UserAccount getUserAccount()
    {
        return this.userAccount;
    }

    public void setUserAccount(UserAccount userAccount)
    {
        this.userAccount = userAccount;
    }

    @Column(name = "USERACCOUNTID", precision = 22, scale = 0)
    public Long getUseraccountid()
    {
        return this.useraccountid;
    }

    public void setUseraccountid(Long useraccountid)
    {
        this.useraccountid = useraccountid;
    }

    @Column(name = "IP", length = 20)
    public String getIp()
    {
        return this.ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    @Column(name = "LOGINDATE")
    public String getLogindate()
    {
        return this.logindate;
    }

    public void setLogindate(String logindate)
    {
        this.logindate = logindate;
    }

    @Column(name = "WRONGCOUNT", precision = 22, scale = 0)
    public Long getWrongcount()
    {
        return this.wrongcount;
    }

    public void setWrongcount(Long wrongcount)
    {
        this.wrongcount = wrongcount;
    }

    @Column(name = "STATUS", length = 6)
    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

}
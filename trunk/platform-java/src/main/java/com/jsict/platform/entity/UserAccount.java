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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.jsict.base.annotation.BusinessLogAnnotation;
import com.jsict.base.annotation.LogType;

/**
 * Useraccount entity.2
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "USER_ACCOUNT", uniqueConstraints = @UniqueConstraint(columnNames = "LOGIN_ID"))
@BusinessLogAnnotation(logType = {LogType.WHEN_ALL, LogType.WHEN_DELETE})
public class UserAccount implements java.io.Serializable
{

    // Fields

    private Long id;

    private Long version;

    private UserAccount creator;

    @BusinessLogAnnotation(property = "name")
    private Company company;

    private String loginId;

    private String name;

    private String email;

    private Subsystem subsystem;

    private String description;

    private Date createdDate;

    private String status;

    private String loginIp;

    private Date loginTime;

    private String password;

    private Integer wrongCount;

    private Set<Messages> messagesesForToid = new HashSet<Messages>(0);

    private Set<UserProfile> userProfiles = new HashSet<UserProfile>(0);

    private Set<UserAccount> userAccounts = new HashSet<UserAccount>(0);

    private Set<UserRole> userRoles = new HashSet<UserRole>(0);

    private Set<Messages> messagesesForFromid = new HashSet<Messages>(0);

    private Set<LoginHistory> loginHistories = new HashSet<LoginHistory>(0);

    // Constructors

    /** default constructor */
    public UserAccount()
    {
    }

    /** minimal constructor */
    public UserAccount(Company company, String loginId, String name,
            String status)
    {
        this.company = company;
        this.loginId = loginId;
        this.name = name;
        this.status = status;
    }

    /** full constructor */
    public UserAccount(Company company, String loginId, String name,
            String email, String description, Date createdDate, String status,
            Set<UserRole> userRoles, Set<UserProfile> userProfiles)
    {
        this.company = company;
        this.loginId = loginId;
        this.name = name;
        this.email = email;
        this.description = description;
        this.createdDate = createdDate;
        this.status = status;
        this.userRoles = userRoles;
        this.userProfiles = userProfiles;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID", nullable = false)
    public Company getCompany()
    {
        return this.company;
    }

    public void setCompany(Company company)
    {
        this.company = company;
    }

    @Column(name = "LOGIN_ID", unique = true, nullable = false, length = 20)
    public String getLoginId()
    {
        return this.loginId;
    }

    public void setLoginId(String loginId)
    {
        this.loginId = loginId;
    }

    @Column(name = "NAME", nullable = false, length = 20)
    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Column(name = "EMAIL", length = 50)
    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBSYSTEM_ID")
    public Subsystem getSubsystem()
    {
        return this.subsystem;
    }

    public void setSubsystem(Subsystem subsystem)
    {
        this.subsystem = subsystem;
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

    @Column(name = "STATUS", nullable = false, length = 6)
    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userAccountByToid")
    public Set<Messages> getMessagesesForToid()
    {
        return this.messagesesForToid;
    }

    public void setMessagesesForToid(Set<Messages> messagesesForToid)
    {
        this.messagesesForToid = messagesesForToid;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "useraccount")
    public Set<UserProfile> getUserProfiles()
    {
        return this.userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles)
    {
        this.userProfiles = userProfiles;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "creator")
    public Set<UserAccount> getUserAccounts()
    {
        return this.userAccounts;
    }

    public void setUserAccounts(Set<UserAccount> userAccounts)
    {
        this.userAccounts = userAccounts;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "useraccount")
    public Set<UserRole> getUserRoles()
    {
        return this.userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles)
    {
        this.userRoles = userRoles;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userAccountByFromid")
    public Set<Messages> getMessagesesForFromid()
    {
        return this.messagesesForFromid;
    }

    public void setMessagesesForFromid(Set<Messages> messagesesForFromid)
    {
        this.messagesesForFromid = messagesesForFromid;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userAccount")
    public Set<LoginHistory> getLoginHistories()
    {
        return this.loginHistories;
    }

    public void setLoginHistories(Set<LoginHistory> loginHistories)
    {
        this.loginHistories = loginHistories;
    }

    @Column(name = "LOGIN_IP", length = 20)
    public String getLoginIp()
    {
        return loginIp;
    }

    public void setLoginIp(String loginIp)
    {
        this.loginIp = loginIp;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOGIN_TIME", length = 7)
    public Date getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Date loginDate)
    {
        this.loginTime = loginDate;
    }

    @Column(name = "PASSWORD", length = 50)
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Column(name = "WRONG_COUNT", length = 20)
    public Integer getWrongCount()
    {
        return wrongCount;
    }

    public void setWrongCount(Integer wrongTime)
    {
        this.wrongCount = wrongTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATOR_ID")
    public UserAccount getCreator()
    {
        return this.creator;
    }

    public void setCreator(UserAccount creator)
    {
        this.creator = creator;
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
        final UserAccount other = (UserAccount) obj;
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
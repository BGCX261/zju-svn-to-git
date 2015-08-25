package com.jsict.platform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * UserRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "USER_ROLE", uniqueConstraints = @UniqueConstraint(columnNames = {
        "USER_ID", "ROLE_ID"}))
public class UserRole implements java.io.Serializable
{

    // Fields

    private Long id;

    private Role role;

    private UserAccount useraccount;

    // Constructors

    /** default constructor */
    public UserRole()
    {
    }

    /** full constructor */
    public UserRole(Role role, UserAccount useraccount)
    {
        this.role = role;
        this.useraccount = useraccount;
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
    @JoinColumn(name = "ROLE_ID", nullable = false)
    public Role getRole()
    {
        return this.role;
    }

    public void setRole(Role role)
    {
        this.role = role;
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
        final UserRole other = (UserRole) obj;
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
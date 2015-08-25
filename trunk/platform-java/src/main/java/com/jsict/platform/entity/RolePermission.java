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
 * RolePermission entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ROLE_PERMISSION", uniqueConstraints = @UniqueConstraint(columnNames = {
        "ROLE_ID", "PERMISSION_ID"}))
public class RolePermission implements java.io.Serializable
{

    // Fields

    private Long id;

    private Role role;

    private Permission permission;

    // Constructors

    /** default constructor */
    public RolePermission()
    {
    }

    /** full constructor */
    public RolePermission(Role role, Permission permission)
    {
        this.role = role;
        this.permission = permission;
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
    @JoinColumn(name = "PERMISSION_ID", nullable = false)
    public Permission getPermission()
    {
        return this.permission;
    }

    public void setPermission(Permission permission)
    {
        this.permission = permission;
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
        final RolePermission other = (RolePermission) obj;
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